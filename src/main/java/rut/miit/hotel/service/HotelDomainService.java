package rut.miit.hotel.service;

import rut.miit.hotel.dto.HotelDto;
import rut.miit.hotel.dto.HotelSearchDto;

import java.time.LocalDate;
import java.util.List;

public interface HotelDomainService {
    List<HotelDto> findAvailableHotelsAndRooms(HotelSearchDto hotelSearchDto);
}
