package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.BookingService;

@Repository
public interface BookingServiceRepository extends JpaRepository<BookingService, Integer> {
}
