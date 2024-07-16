package rut.miit.hotel.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.repositories.RoomRepository;

import java.util.List;

@Repository
public class RoomRepositoryImpl extends BaseRepository<Room, Integer> implements RoomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public RoomRepositoryImpl() {
        super(Room.class);
    }

    public List<Room> findRoomsByHotelId(@Param("hotelId") Integer id) {
        return entityManager.createQuery("SELECT r FROM Room r WHERE r.hotel.id = :hotelId", Room.class)
                .setParameter("hotelId", id)
                .getResultList();
    }

}
