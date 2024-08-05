package rut.miit.hotel.service;

import rut.miit.hotel.dto.response.HotelOptionResponseDto;

import java.util.List;

public interface HotelOptionDomainService {
    List<HotelOptionResponseDto> recommendOptions(Integer customer, Integer hotel);
    List<HotelOptionResponseDto> findByHotel(Integer id);
    List<HotelOptionResponseDto> findByCustomer(Integer id);
}
