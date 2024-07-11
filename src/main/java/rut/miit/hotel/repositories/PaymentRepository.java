package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
