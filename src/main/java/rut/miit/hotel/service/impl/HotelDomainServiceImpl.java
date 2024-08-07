package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rut.miit.hotel.domain.Hotel;
import rut.miit.hotel.dto.response.HotelResponseDto;
import rut.miit.hotel.dto.response.RoomResponseDto;
import rut.miit.hotel.service.HotelDomainService;
import rut.miit.hotel.repository.HotelRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelDomainServiceImpl implements HotelDomainService {

    private final HotelRepository hotelRepository;
    private final BookingDomainServiceImpl bookingDomainService;
    private final ModelMapper modelMapper;

    public HotelDomainServiceImpl(HotelRepository hotelRepository, BookingDomainServiceImpl bookingService, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.bookingDomainService = bookingService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HotelResponseDto> findAvailableHotelsAndRooms(LocalDate startDate, LocalDate endDate, Byte capacity,
                                                              Integer maxPrice, String country, String city, Byte rating) {

        List<Hotel> hotels = hotelRepository.findByAttributes(country, city, rating);
        List<HotelResponseDto> availableHotelsDto = new ArrayList<>();
        for (Hotel hotel : hotels) {
            List<RoomResponseDto> availableRooms = hotel.getRooms().stream()
                    .filter(room -> bookingDomainService.isRoomAvailable(room, startDate, endDate))
                    .filter(room -> capacity == null || room.getCapacity() >= capacity)
                    .filter(room -> maxPrice == null || room.getPricePerNight().compareTo(maxPrice) <= 0)
                    .map(room -> modelMapper.map(room, RoomResponseDto.class))
                    .toList();

            if (!availableRooms.isEmpty()) {
                HotelResponseDto hotelResponseDto = modelMapper.map(hotel, HotelResponseDto.class);
                hotelResponseDto.setRooms(availableRooms);
                availableHotelsDto.add(hotelResponseDto);
            }
        }

        return availableHotelsDto;
    }
}
