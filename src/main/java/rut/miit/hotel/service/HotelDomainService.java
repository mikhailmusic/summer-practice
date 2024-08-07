package rut.miit.hotel.service;

import rut.miit.hotel.dto.response.HotelResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface HotelDomainService {
    List<HotelResponseDto> findAvailableHotelsAndRooms(LocalDate startDate, LocalDate endDate, Byte capacity,
                                                       Integer maxPrice, String country, String city, Byte rating);
}
