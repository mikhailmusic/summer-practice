package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.BookingOption;

@Repository
public interface BookingOptionRepository extends JpaRepository<BookingOption, Integer> {
}
