package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.entity.Booking;
import rut.miit.hotel.domain.entity.Payment;
import rut.miit.hotel.domain.entity.status.PaymentStatus;

import java.util.List;

@Repository
public interface PaymentRepository extends GeneralRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p " +
            "WHERE p.booking = :booking " +
            "AND p.status IN :statuses")
    List<Payment> findPaymentsByBookingAndStatuses(
            @Param("booking") Booking booking,
            @Param("statuses") List<PaymentStatus> statuses);
}
