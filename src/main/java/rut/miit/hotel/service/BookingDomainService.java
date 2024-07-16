package rut.miit.hotel.service;

import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.BookingOption;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.dto.request.BookingRequestDto;
import rut.miit.hotel.dto.request.PaymentRequestDto;
import rut.miit.hotel.dto.response.BookingResponseDto;
import rut.miit.hotel.dto.response.PaymentResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface BookingDomainService {
    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);
    boolean checkBookings(Customer customer, LocalDate startDate, LocalDate endDate);
    boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate);
    boolean isBookingExpired(Booking booking);
    Double calculateTotalAmount(Room room, LocalDate startDate, LocalDate endDate, List<BookingOption> bookingOptions);
    void payPayment(PaymentRequestDto paymentRequestDto);
    BookingResponseDto cancelBooking(Integer booking);

}
