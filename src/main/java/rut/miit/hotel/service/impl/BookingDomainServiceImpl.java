package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rut.miit.hotel.domain.*;
import rut.miit.hotel.domain.status.BookingStatus;
import rut.miit.hotel.domain.status.PaymentStatus;
import rut.miit.hotel.dto.BookingDto;
import rut.miit.hotel.dto.BookingOptionDto;
import rut.miit.hotel.dto.PaymentDto;
import rut.miit.hotel.exception.*;
import rut.miit.hotel.repository.*;
import rut.miit.hotel.service.BookingDomainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class BookingDomainServiceImpl implements BookingDomainService {
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
    public BookingDto createBooking(BookingDto bookingDto) {
        Customer customer = customerRepository.findById(bookingDto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException());
        Room room = roomRepository.findById(bookingDto.getRoomId()).orElseThrow(() -> new RoomNotFoundException());
        LocalDate startDate = bookingDto.getStartDate();
        LocalDate endDate = bookingDto.getEndDate();

        if (!isRoomAvailable(room, startDate, endDate)) {
            throw new ValidationException("Room is already booked for the specified dates or is not valid");
        }
        if (hasBookingInRange(customer, startDate, endDate)) {
            throw new ValidationException("Customer already has a booking (paid or unpaid) for the selected period");
        }
        Booking booking = new Booking(startDate, endDate, room, customer);

        List<BookingOption> bookingOptions = new ArrayList<>();
        if (bookingDto.getBookingOptions() != null){

            for (BookingOptionDto dto : bookingDto.getBookingOptions()) {
                HotelOption hotelOption = hotelOptionRepository.findById(dto.getHotelOptionId())
                        .orElseThrow(() -> new HotelOptionNotFoundException());

                if (!hotelOption.getHotel().getId().equals(room.getHotel().getId())) throw new ValidationException("HotelOption incorrect");
                bookingOptions.add(new BookingOption(booking, hotelOption, dto.getCount()));
            }
            booking.setBookingOptions(bookingOptions);
        }

        long totalPrice = calculateTotalAmount(room, startDate, endDate, bookingOptions);
        Payment payment = new Payment(totalPrice, booking);

        booking.setPayments(Arrays.asList(payment));
        bookingRepository.save(booking);

        return modelMapper.map(booking, BookingDto.class);
    }

    boolean hasBookingInRange(Customer customer, LocalDate startDate, LocalDate endDate) {
        List<Booking> overlappingBookings = bookingRepository.findByCustomerDateRangeStatuses(customer, startDate, endDate, List.of(BookingStatus.CREATED, BookingStatus.COMPLETED));
        return !overlappingBookings.isEmpty();
    }

    boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        if (!room.isFunctional()) return false;
        List<Booking> bookings = bookingRepository.findByRoomAndDateRange(room, startDate, endDate);
        for (Booking booking : bookings) {
            if (booking.checkValid()) {
                return false;
            }
        }
        return true;
    }


    long calculateTotalAmount(Room room, LocalDate startDate, LocalDate endDate, List<BookingOption> bookingOptions) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        long total = room.getPricePerNight() * days;

        for (BookingOption bookingOption : bookingOptions) {
            total += bookingOption.getHotelOption().getPrice() * bookingOption.getCount();
        }
        if (days > 20) total -= (long) (total * 0.1);

        return total;
    }

    @Override
    public BookingDto cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));

        switch (booking.getStatus()) {
            case CANCELLED -> throw new ValidationException("Booking is already cancelled");
            case CREATED -> {
                Payment oldPayment = paymentRepository.findByBookingAndStatus(booking, PaymentStatus.CREATED);
                oldPayment.setStatus(PaymentStatus.CANCELLED);
                paymentRepository.update(oldPayment);
            }
            case COMPLETED -> {
                LocalDateTime startDateTime = booking.getStartDate().atTime(booking.getRoom().getHotel().getCheckInTime());
                boolean isWithinTimeFrame =  LocalDateTime.now().plusHours(24).isBefore(startDateTime);

                long penaltyAmount = 0;
                if (!isWithinTimeFrame) {
                    long days = ChronoUnit.DAYS.between(LocalDate.now(), booking.getEndDate());
                    if (days <= 0) throw new ValidationException("Booking cannot be refunded as the booking period has ended");

                    penaltyAmount = booking.getRoom().getPricePerNight() * (days + 1);
                }
                Payment oldPayment = paymentRepository.findByBookingAndStatus(booking, PaymentStatus.COMPLETED);
                Payment payment = new Payment(oldPayment.getAmount() - penaltyAmount, LocalDateTime.now(),
                        oldPayment.getBankName(), oldPayment.getBankAccount(), PaymentStatus.RETURNED, booking);
                booking.addPayment(payment);

            }
            default -> throw new ValidationException("Booking is invalid");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.update(booking);

        return modelMapper.map(booking, BookingDto.class);

    }

    @Override
    public void payPayment(PaymentDto paymentDto) {
        Payment payment = paymentRepository.findById(paymentDto.getId()).orElseThrow(() -> new PaymentNotFoundException(paymentDto.getId()));
        Booking booking = payment.getBooking();

        if (!(booking.getStatus().equals(BookingStatus.CREATED))) {
            throw new ValidationException("Booking is cancelled or already paid");
        }
        if (ChronoUnit.MINUTES.between(booking.getCreatedAt(), LocalDateTime.now()) >= 60) {
            throw new ValidationException("Payment is required within 60 minutes of booking creation");
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
