package rut.miit.hotel.repository;

import rut.miit.hotel.domain.HotelOption;
import rut.miit.hotel.domain.Option;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HotelOptionRepository {
    Optional<HotelOption> findById(Integer id);
    List<HotelOption> findByHotelId(Integer hotelId);
    List<HotelOption> findByHotelIdAndOptions(Integer hotelId, Set<Option> options);
    List<HotelOption> findByCustomerId(Integer customerId);
}
