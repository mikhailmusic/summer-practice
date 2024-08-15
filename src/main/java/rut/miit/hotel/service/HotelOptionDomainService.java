package rut.miit.hotel.service;

import rut.miit.hotel.dto.HotelOptionDto;

import java.util.List;

public interface HotelOptionDomainService {
    List<HotelOptionDto> getRecommendOptions(Integer customer, Integer hotel);
    List<HotelOptionDto> findByHotel(Integer id);
    List<HotelOptionDto> findByCustomer(Integer id);
}
