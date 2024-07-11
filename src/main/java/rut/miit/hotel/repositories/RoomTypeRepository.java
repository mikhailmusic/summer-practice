package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
}
