package rut.miit.hotel.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Payment;
import rut.miit.hotel.domain.status.PaymentStatus;
import rut.miit.hotel.repositories.PaymentRepository;

import java.util.List;

@Repository
public class PaymentRepositoryImpl extends BaseRepository<Payment, Integer> implements PaymentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PaymentRepositoryImpl() {
        super(Payment.class);
    }

    public List<Payment> findPaymentsByBookingAndStatuses(Booking booking, List<PaymentStatus> statuses) {
        return entityManager.createQuery("SELECT p FROM Payment p WHERE p.booking = :booking AND p.status IN :statuses", Payment.class)
                .setParameter("booking", booking).setParameter("statuses", statuses)
                .getResultList();
    }
}
