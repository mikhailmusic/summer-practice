package rut.miit.hotel.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Payment;
import rut.miit.hotel.domain.status.PaymentStatus;
import rut.miit.hotel.repositories.PaymentRepository;


@Repository
public class PaymentRepositoryImpl extends BaseRepository<Payment, Integer> implements PaymentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PaymentRepositoryImpl() {
        super(Payment.class);
    }

    public Payment findByBookingAndStatus(Booking booking, PaymentStatus status) {
        return entityManager.createQuery("SELECT p FROM Payment p WHERE p.booking = :booking AND p.status = :status", Payment.class)
                .setParameter("booking", booking).setParameter("status", status)
                .getSingleResult();
    }
}
