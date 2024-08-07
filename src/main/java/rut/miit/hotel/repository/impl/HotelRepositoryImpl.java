package rut.miit.hotel.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Hotel;
import rut.miit.hotel.repository.HotelRepository;

import java.util.List;


@Repository
public class HotelRepositoryImpl extends BaseRepository<Hotel, Integer> implements HotelRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public HotelRepositoryImpl() {
        super(Hotel.class);
    }

    public List<Hotel> findByAttributes(String country, String city, Byte rating) {
        return entityManager.createQuery("SELECT h FROM Hotel h WHERE (:country IS NULL OR h.country LIKE :country) AND " +
                "(:city IS NULL OR h.city LIKE :city) AND " +
                "(:rating IS NULL OR h.rating = :rating)", Hotel.class)
                .setParameter("country",country ).setParameter("city", city).setParameter("rating", rating)
                .getResultList();
    }
}
