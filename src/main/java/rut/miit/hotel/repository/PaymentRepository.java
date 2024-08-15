package rut.miit.hotel.repository;

import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Payment;
import rut.miit.hotel.domain.status.PaymentStatus;

import java.util.Optional;


public interface PaymentRepository {
    Optional<Payment> findById(Integer id);
    Payment save(Payment payment);
    Payment update(Payment payment);
    Payment findByBookingAndStatus(Booking booking, PaymentStatus status);

}
