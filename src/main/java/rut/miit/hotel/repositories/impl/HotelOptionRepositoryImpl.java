package rut.miit.hotel.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.HotelOption;
import rut.miit.hotel.repositories.HotelOptionRepository;

import java.util.List;

@Repository
public class HotelOptionRepositoryImpl extends BaseRepository<HotelOption, Integer> implements HotelOptionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public HotelOptionRepositoryImpl() {
        super(HotelOption.class);
    }

    public List<HotelOption> findHotelOptionsByHotelId(Integer hotelId) {
        return entityManager.createQuery("SELECT ho FROM HotelOption ho WHERE ho.hotel.id = :hotelId", HotelOption.class)
                .setParameter("hotelId", hotelId)
                .getResultList();
    }

    // найти все опции отеля, которые клиент добавлял к брони
    public List<HotelOption> findOptionsByCustomer(@Param("customerId") Integer customerId) {
        return entityManager.createQuery("SELECT ho FROM Customer c JOIN c.bookings b JOIN b.bookingOptions bo " +
                "JOIN bo.hotelOption ho WHERE c.id = :customerId", HotelOption.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
