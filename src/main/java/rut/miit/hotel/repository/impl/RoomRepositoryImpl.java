package rut.miit.hotel.repository.impl;

import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.repository.RoomRepository;


@Repository
public class RoomRepositoryImpl extends BaseRepository<Room, Integer> implements RoomRepository {

    public RoomRepositoryImpl() {
        super(Room.class);
    }

}
