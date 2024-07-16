package rut.miit.hotel.repositories;

import rut.miit.hotel.domain.Hotel;

import java.util.List;
import java.util.Optional;


public interface HotelRepository {
    Optional<Hotel> findById(Integer id);
    List<Hotel> findByCountryAndCityAndRating(String country, String city, Integer rating);
}
