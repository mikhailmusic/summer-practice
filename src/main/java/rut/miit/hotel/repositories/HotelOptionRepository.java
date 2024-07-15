package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.entity.Customer;
import rut.miit.hotel.domain.entity.HotelOption;

import java.util.List;

@Repository
public interface HotelOptionRepository extends GeneralRepository<HotelOption, Integer> {
    // найти доп. услуги отелей с заданным диапазоном цен
    @Query("SELECT ho FROM HotelOption ho WHERE ho.price BETWEEN :minPrice AND :maxPrice")
    List<HotelOption> findHotelOptionsByPriceRange(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);

    // найти все опции отеля, которые клиент добавлял к брони
    @Query("SELECT ho FROM Customer c " +
            "JOIN c.bookings b JOIN b.bookingOptions bo JOIN bo.hotelOption ho " +
            "WHERE c = :customer")
    List<HotelOption> findOptionsByCustomer(@Param("customer") Customer customer);
}
