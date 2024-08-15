package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rut.miit.hotel.domain.*;
import rut.miit.hotel.domain.status.BookingStatus;
import rut.miit.hotel.domain.status.PaymentStatus;
import rut.miit.hotel.dto.BookingDto;
import rut.miit.hotel.dto.BookingOptionDto;
import rut.miit.hotel.dto.PaymentDto;
import rut.miit.hotel.exception.EntityNotFoundException;
import rut.miit.hotel.exception.ValidationFailedException;
import rut.miit.hotel.repository.*;
import rut.miit.hotel.service.BookingDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingDomainServiceImpl implements BookingDomainService {
    public static final int DISCOUNT_DAYS = 20;
    public static final int DISCOUNT_PERCENT = 10;
    public static final int FREE_CANCEL_HOURS = 24;
    public static final int PENALTY_DAYS = 1;
    public static final int BOOKING_TIMEOUT_MINUTES = 60;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final HotelOptionRepository hotelOptionRepository;
    private final ModelMapper modelMapper;

    public BookingDomainServiceImpl(BookingRepository bookingRepository, PaymentRepository paymentRepository, CustomerRepository customerRepository, RoomRepository roomRepository, HotelOptionRepository hotelOptionRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.hotelOptionRepository = hotelOptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public BookingDto createBooking(BookingDto bookingDto) {
        Customer customer = customerRepository.findById(bookingDto.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Room room = roomRepository.findById(bookingDto.getRoomId()).orElseThrow(() -> new EntityNotFoundException("Room not found"));
        LocalDate startDate = bookingDto.getStartDate();
        LocalDate endDate = bookingDto.getEndDate();

        if (!isRoomAvailable(room, startDate, endDate)) {
            throw new ValidationFailedException("Room is already booked for the specified dates or is not valid");
        }
        if (hasBookingInRange(customer, startDate, endDate)) {
            throw new ValidationFailedException("Customer already has a booking (paid or unpaid) for the selected period");
        }
        Booking booking = new Booking(startDate, endDate, room, customer);

        List<BookingOption> bookingOptions = new ArrayList<>();
        if (bookingDto.getBookingOptions() != null){

            for (BookingOptionDto dto : bookingDto.getBookingOptions()) {
                HotelOption hotelOption = hotelOptionRepository.findById(dto.getHotelOptionId())
                        .orElseThrow(() -> new EntityNotFoundException("Hotel option not found"));

                if (!hotelOption.getHotel().getId().equals(room.getHotel().getId())) throw new ValidationFailedException("HotelOption incorrect");
                bookingOptions.add(new BookingOption(booking, hotelOption, dto.getCount()));
            }
            booking.setBookingOptions(bookingOptions);
        }

        double totalPrice = calculateTotalAmount(room, startDate, endDate, bookingOptions);
        Payment payment = new Payment(totalPrice, booking);

        booking.setPayments(List.of(payment));
        bookingRepository.save(booking);

        return modelMapper.map(booking, BookingDto.class);
    }

    protected boolean hasBookingInRange(Customer customer, LocalDate startDate, LocalDate endDate) {
        List<Booking> overlappingBookings = bookingRepository.findByCustomerDateRangeStatuses(customer, startDate, endDate, List.of(BookingStatus.CREATED, BookingStatus.COMPLETED));
        return !overlappingBookings.isEmpty();
    }

    protected boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        if (!room.isFunctional()) return false;
        List<Booking> bookings = bookingRepository.findByRoomAndDateRange(room, startDate, endDate);
        for (Booking booking : bookings) {
            if (isBookingActive(booking)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isBookingActive(Booking booking) {
        BookingStatus status = booking.getStatus();
        LocalDateTime createdAt = booking.getCreatedAt();
        LocalDateTime currentDateTime = LocalDateTime.now();
        return status.equals(BookingStatus.COMPLETED) ||
                status.equals(BookingStatus.CREATED) && ChronoUnit.MINUTES.between(createdAt, currentDateTime) < BOOKING_TIMEOUT_MINUTES;
    }

    protected double calculateTotalAmount(Room room, LocalDate startDate, LocalDate endDate, List<BookingOption> bookingOptions) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        double total = room.getPricePerNight() * days;

        for (BookingOption bookingOption : bookingOptions) {
            total += bookingOption.getHotelOption().getPrice() * bookingOption.getCount();
        }
        if (days > DISCOUNT_DAYS) total -= Math.floor(total * DISCOUNT_PERCENT / 100.0);

        return total;
    }

    @Override
    @Transactional
    public BookingDto cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        switch (booking.getStatus()) {
            case CANCELLED -> throw new ValidationFailedException("Booking is already cancelled");
            case CREATED -> {
                Payment oldPayment = paymentRepository.findByBookingAndStatus(booking, PaymentStatus.CREATED);
                oldPayment.setStatus(PaymentStatus.CANCELLED);
                paymentRepository.update(oldPayment);
            }
            case COMPLETED -> {
                LocalDateTime startDateTime = booking.getStartDate().atTime(booking.getRoom().getHotel().getCheckInTime());
                boolean isWithinTimeFrame =  LocalDateTime.now().plusHours(FREE_CANCEL_HOURS).isBefore(startDateTime);

                double penaltyAmount = 0;
                if (!isWithinTimeFrame) {
                    long days = ChronoUnit.DAYS.between(LocalDate.now(), booking.getEndDate());
                    if (days <= 0) throw new ValidationFailedException("Booking cannot be refunded as the booking period has ended");

                    penaltyAmount = booking.getRoom().getPricePerNight() * (days + PENALTY_DAYS);
                }
                Payment oldPayment = paymentRepository.findByBookingAndStatus(booking, PaymentStatus.COMPLETED);
                Payment payment = new Payment(oldPayment.getAmount() - penaltyAmount, LocalDateTime.now(),
                        oldPayment.getBankName(), oldPayment.getBankAccount(), PaymentStatus.RETURNED, booking);
                paymentRepository.save(payment);

            }
            default -> throw new ValidationFailedException("Booking is invalid");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.update(booking);

        return modelMapper.map(booking, BookingDto.class);

    }

    @Override
    @Transactional
    public void payPayment(PaymentDto paymentDto) {
        Payment payment = paymentRepository.findById(paymentDto.getId()).orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        Booking booking = payment.getBooking();

        if (!(booking.getStatus().equals(BookingStatus.CREATED))) {
            throw new ValidationFailedException("Booking is cancelled or already paid");
        }
        if (ChronoUnit.MINUTES.between(booking.getCreatedAt(), LocalDateTime.now()) >= BOOKING_TIMEOUT_MINUTES) {
            throw new ValidationFailedException("Payment is required within " + BOOKING_TIMEOUT_MINUTES + " minutes of booking creation");
        }

        payment.setBankName(paymentDto.getBankName());
        payment.setBankAccount(paymentDto.getBankAccount());

        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setDateOfPayment(LocalDateTime.now());
        paymentRepository.update(payment);

        booking.setStatus(BookingStatus.COMPLETED);
        bookingRepository.update(booking);
    }

}
