package rut.miit.hotel.service;

import rut.miit.hotel.dto.request.BookingRequestDto;
import rut.miit.hotel.dto.request.PaymentRequestDto;
import rut.miit.hotel.dto.response.BookingResponseDto;

public interface BookingDomainService {
    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);
    void payPayment(PaymentRequestDto paymentRequestDto);
    BookingResponseDto cancelBooking(Integer booking);

}
