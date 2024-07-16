package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rut.miit.hotel.domain.*;
import rut.miit.hotel.domain.status.BookingStatus;
import rut.miit.hotel.domain.status.PaymentStatus;
import rut.miit.hotel.dto.request.BookingOptionRequestDto;
import rut.miit.hotel.dto.request.BookingRequestDto;
import rut.miit.hotel.dto.request.PaymentRequestDto;
import rut.miit.hotel.dto.response.BookingResponseDto;
import rut.miit.hotel.exception.NotFoundException;
import rut.miit.hotel.exception.ValidationException;
import rut.miit.hotel.repositories.*;
import rut.miit.hotel.service.BookingDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingDomainServiceImpl implements BookingDomainService {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final HotelOptionRepository hotelOptionRepository;
    private final BookingOptionRepository bookingOptionRepository;
    private final ModelMapper modelMapper;
    public static final int DISCOUNT_DAYS = 20;
    public static final int DISCOUNT_PERCENT = 10;
    public static final int FREE_CANCEL_HOURS = 24;
    public static final int PENALTY_DAYS = 1;
    public static final int BOOKING_TIMEOUT_MINUTES = 60;

    public BookingDomainServiceImpl(BookingRepository bookingRepository, PaymentRepository paymentRepository, CustomerRepository customerRepository, RoomRepository roomRepository, HotelOptionRepository hotelOptionRepository, BookingOptionRepository bookingOptionRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.hotelOptionRepository = hotelOptionRepository;
        this.bookingOptionRepository = bookingOptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        Customer customer = customerRepository.findById(bookingRequestDto.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found"));
        Room room = roomRepository.findById(bookingRequestDto.getRoomId()).orElseThrow(() -> new NotFoundException("Room not found"));
        LocalDate startDate = bookingRequestDto.getStartDate();
        LocalDate endDate = bookingRequestDto.getEndDate();

        if (!isRoomAvailable(room, startDate, endDate)) {
            throw new ValidationException("The room is already booked for the specified dates or is not valid");
        }
        if (checkBookings(customer, startDate, endDate)) {
            throw new ValidationException("The room is already booked for the specified dates");
        }
        Booking booking = new Booking(startDate, endDate, room, customer);
        bookingRepository.save(booking);

        List<BookingOption> bookingOptions = new ArrayList<>();
        if (!bookingRequestDto.getBookingOptions().isEmpty()){
            for (BookingOptionRequestDto dto : bookingRequestDto.getBookingOptions()) {
                HotelOption hotelOption = hotelOptionRepository.findById(dto.getHotelOptionId())
                        .orElseThrow(() -> new NotFoundException("Hotel option not found"));

                if (!hotelOption.getHotel().getId().equals(room.getHotel().getId())) throw new ValidationException("HotelOption incorrect");
                BookingOption bookingOption = new BookingOption(booking, hotelOption, dto.getCount());
                bookingOptions.add(bookingOption);
                bookingOptionRepository.save(bookingOption);
            }
        }

        double totalPrice = calculateTotalAmount(room, startDate, endDate, bookingOptions);
        Payment payment = new Payment(totalPrice, booking);
        paymentRepository.save(payment);

        booking.setBookingOptions(bookingOptions);
        booking.setPayments(List.of(payment));

        return modelMapper.map(booking, BookingResponseDto.class);
    }

    @Override
    public boolean checkBookings(Customer customer, LocalDate startDate, LocalDate endDate) {
        List<Booking> overlappingBookings = bookingRepository.findByCustomerDateRangeStatuses(customer, startDate, endDate, List.of(BookingStatus.COMPLETED));
        if (!overlappingBookings.isEmpty()) {
            throw new ValidationException("Customer already has a booking in the selected period");
        }
        return false;
    }

    @Override
    public boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        if (!room.isFunctional()) return false;
        List<Booking> bookings = bookingRepository.findBookingsByRoomAndDateRange(room, startDate, endDate);
        for (Booking booking : bookings) {
            if (booking.getBookingStatus().equals(BookingStatus.COMPLETED) || isBookingExpired(booking)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isBookingExpired(Booking booking) {
        LocalDateTime createdAt = booking.getCreatedAt();
        LocalDateTime currentDateTime = LocalDateTime.now();
        boolean isExpired = booking.getBookingStatus().equals(BookingStatus.CREATED)
                && ChronoUnit.MINUTES.between(createdAt, currentDateTime) >= BOOKING_TIMEOUT_MINUTES;

        if (isExpired) {
            booking.setBookingStatus(BookingStatus.CANCELED);
            Payment payment = booking.getPayments().getFirst();
            if (payment != null) {
                payment.setStatus(PaymentStatus.EXPIRED);
                paymentRepository.save(payment);
            }
            bookingRepository.save(booking);
        }

        return isExpired;
    }

    @Override
    public Double calculateTotalAmount(Room room, LocalDate startDate, LocalDate endDate, List<BookingOption> bookingOptions) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        double total = room.getPricePerNight() * days;

        for (BookingOption bookingOption : bookingOptions) {
            total += (bookingOption.getHotelOption().getPrice() * bookingOption.getCount());
        }
        if (days > DISCOUNT_DAYS) total -= Math.floor(total * DISCOUNT_PERCENT / 100.0);

        return total;
    }

    @Override
    @Transactional
    public BookingResponseDto cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking not found"));

        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime startDate = booking.getStartDate().atTime(booking.getRoom().getHotel().getCheckInTime());

        boolean isWithinTimeFrame = nowDate.plusHours(FREE_CANCEL_HOURS).isBefore(startDate);
        boolean isBookingExpired = nowDate.isAfter(booking.getEndDate().atTime(booking.getRoom().getHotel().getCheckOutTime()));

        if (isBookingExpired) {
            throw new ValidationException("Booking cannot be refunded as the booking period has ended.");
        }

        if (!isWithinTimeFrame) {
            double pricePerNight = booking.getRoom().getPricePerNight();
            long days = ChronoUnit.DAYS.between(nowDate, startDate);
            refundPayment(booking, pricePerNight * (days + PENALTY_DAYS));

        } else {
            refundPayment(booking, 0);
        }

        booking.setBookingStatus(BookingStatus.CANCELED);
        bookingRepository.save(booking);

        return modelMapper.map(booking, BookingResponseDto.class);

    }

    @Transactional
    protected void refundPayment(Booking booking, double penaltyAmount) {
        if (booking.getBookingStatus().equals(BookingStatus.CREATED)){
            Payment oldPayment = paymentRepository.findPaymentsByBookingAndStatuses(booking, List.of(PaymentStatus.CREATED)).getFirst();
            oldPayment.setStatus(PaymentStatus.CANCELED);
            paymentRepository.save(oldPayment);
            return;
        }

        Payment oldPayment = paymentRepository.findPaymentsByBookingAndStatuses(booking, List.of(PaymentStatus.COMPLETED)).getFirst();
        Payment payment = new Payment(oldPayment.getAmount() - penaltyAmount, oldPayment.getBankName(), oldPayment.getBankAccount(), booking);
        payment.setBooking(booking);
        payment.setDateOfPayment(LocalDateTime.now());
        payment.setStatus(PaymentStatus.RETURNED);
        paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public void payPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentRepository.findById(paymentRequestDto.getId()).orElseThrow(() -> new NotFoundException("Payment not found"));
        if (isBookingExpired(payment.getBooking())) throw new ValidationException("Booking created for specified time has already ended");

        payment.setBankName(paymentRequestDto.getBankName());
        payment.setBankAccount(paymentRequestDto.getBankAccount());
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setDateOfPayment(LocalDateTime.now());
        paymentRepository.save(payment);

        Booking booking = payment.getBooking();
        booking.setBookingStatus(BookingStatus.COMPLETED);
        bookingRepository.save(booking);
    }

}
