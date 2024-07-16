package rut.miit.hotel.repositories;

import rut.miit.hotel.domain.HotelOption;

import java.util.List;
import java.util.Optional;

public interface HotelOptionRepository {
    Optional<HotelOption> findById(Integer id);
    List<HotelOption> findHotelOptionsByHotelId(Integer hotelId);
    List<HotelOption> findOptionsByCustomer(Integer customerId);
}
