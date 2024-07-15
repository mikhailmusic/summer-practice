package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.entity.Customer;
import rut.miit.hotel.domain.entity.Hotel;
import rut.miit.hotel.domain.entity.HotelOption;
import rut.miit.hotel.domain.entity.Option;

import java.util.List;

@Repository
public interface OptionRepository extends GeneralRepository<Option, Integer> {
    // услуги, предоставляемые в определенном отеле
    @Query("SELECT o FROM Option o JOIN HotelOption ho ON o.id = ho.option.id WHERE ho.hotel = :hotel")
    List<Option> findAllOptionsByHotel(@Param("hotel") Hotel hotel);

    // найти все опции отеля, которые клиент добавлял к брони
    @Query("SELECT o FROM Customer c " +
            "JOIN c.bookings b JOIN b.bookingOptions bo JOIN bo.hotelOption ho JOIN ho.option o " +
            "WHERE c = :customer")
    List<HotelOption> findOptionsByCustomer(@Param("customer") Customer customer);

}
