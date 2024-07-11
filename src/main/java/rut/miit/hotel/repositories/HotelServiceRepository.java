package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.HotelService;

@Repository
public interface HotelServiceRepository extends JpaRepository<HotelService, Integer> {
}
