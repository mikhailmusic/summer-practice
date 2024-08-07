package rut.miit.hotel.repository;

import rut.miit.hotel.domain.Hotel;

import java.util.List;


public interface HotelRepository {
    List<Hotel> findByAttributes(String country, String city, Integer rating);
}
