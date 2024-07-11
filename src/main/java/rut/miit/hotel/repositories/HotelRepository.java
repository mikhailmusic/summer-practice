package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
