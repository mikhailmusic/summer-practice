package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.HotelOption;

@Repository
public interface HotelOptionRepository extends JpaRepository<HotelOption, Integer> {
}
