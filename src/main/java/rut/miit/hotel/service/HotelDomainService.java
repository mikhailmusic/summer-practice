package rut.miit.hotel.service;

import rut.miit.hotel.dto.HotelDto;

import java.time.LocalDate;
import java.util.List;

public interface HotelDomainService {
    List<HotelDto> findAvailableHotelsAndRooms(LocalDate startDate, LocalDate endDate, Byte capacity,
                                               Integer maxPrice, String country, String city, Byte rating);
}
