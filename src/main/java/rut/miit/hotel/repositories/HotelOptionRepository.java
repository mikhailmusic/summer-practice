package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.HotelOption;

import java.util.List;

@Repository
public interface HotelOptionRepository extends GeneralRepository<HotelOption, Integer> {
    @Query("SELECT ho FROM HotelOption ho WHERE ho.hotel.id = :hotelId")
    List<HotelOption> findHotelOptionsByHotelId(@Param("hotelId") Integer hotelId);

    // найти все опции отеля, которые клиент добавлял к брони
    @Query("SELECT ho FROM Customer c " +
            "JOIN c.bookings b JOIN b.bookingOptions bo JOIN bo.hotelOption ho " +
            "WHERE c.id = :customerId")
    List<HotelOption> findOptionsByCustomer(@Param("customerId") Integer customerId);
}
