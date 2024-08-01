package rut.miit.hotel.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.HotelOption;
import rut.miit.hotel.domain.Option;
import rut.miit.hotel.repositories.HotelOptionRepository;

import java.util.List;
import java.util.Set;

@Repository
public class HotelOptionRepositoryImpl extends BaseRepository<HotelOption, Integer> implements HotelOptionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public HotelOptionRepositoryImpl() {
        super(HotelOption.class);
    }

    public List<HotelOption> findByHotelId(Integer hotelId) {
        return entityManager.createQuery("SELECT ho FROM HotelOption ho WHERE ho.hotel.id = :hotelId", HotelOption.class)
                .setParameter("hotelId", hotelId)
                .getResultList();
    }

    public List<HotelOption> findByHotelIdAndOptions(Integer hotelId, Set<Option> options) {
        return entityManager.createQuery("SELECT ho FROM HotelOption ho WHERE ho.hotel.id = :hotelId AND ho.option IN :options", HotelOption.class)
                .setParameter("hotelId", hotelId).setParameter("options", options)
                .getResultList();
    }

    // найти все опции отеля, которые клиент добавлял к брони
    public List<HotelOption> findByCustomer(@Param("customerId") Integer customerId) {
        return entityManager.createQuery("SELECT ho FROM Customer c JOIN c.bookings b JOIN b.bookingOptions bo " +
                "JOIN bo.hotelOption ho WHERE c.id = :customerId", HotelOption.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
