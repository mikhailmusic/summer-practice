package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
