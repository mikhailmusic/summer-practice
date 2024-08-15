package rut.miit.hotel.service;

import rut.miit.hotel.dto.BookingDto;
import rut.miit.hotel.dto.PaymentDto;

public interface BookingDomainService {
    BookingDto createBooking(BookingDto bookingDto);
    void payPayment(PaymentDto paymentDto);
    BookingDto cancelBooking(Integer booking);

}
