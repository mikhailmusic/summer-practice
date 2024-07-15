package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends GeneralRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId")
    List<Room> findRoomsByHotelId(@Param("hotelId") Integer id);

}
