package rut.miit.hotel.repositories.impl;

import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.repositories.RoomRepository;


@Repository
public class RoomRepositoryImpl extends BaseRepository<Room, Integer> implements RoomRepository {

    public RoomRepositoryImpl() {
        super(Room.class);
    }

}
