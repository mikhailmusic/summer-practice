package rut.miit.hotel.repository;

import rut.miit.hotel.domain.Room;


import java.util.Optional;

public interface RoomRepository {
    Optional<Room> findById(Integer id);

}
