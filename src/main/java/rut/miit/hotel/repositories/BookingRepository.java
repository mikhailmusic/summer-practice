package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
