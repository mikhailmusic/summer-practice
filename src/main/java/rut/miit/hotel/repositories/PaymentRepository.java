package rut.miit.hotel.repositories;

import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Payment;
import rut.miit.hotel.domain.status.PaymentStatus;

import java.util.List;
import java.util.Optional;


public interface PaymentRepository {
    Optional<Payment> findById(Integer id);
    Payment save(Payment payment);
    Payment update(Payment payment);
    List<Payment> findPaymentsByBookingAndStatuses(Booking booking, List<PaymentStatus> statuses);

}
