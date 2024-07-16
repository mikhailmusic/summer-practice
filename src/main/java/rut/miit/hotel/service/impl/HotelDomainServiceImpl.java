package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rut.miit.hotel.domain.Hotel;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.dto.response.HotelResponseDto;
import rut.miit.hotel.service.BookingDomainService;
import rut.miit.hotel.service.HotelDomainService;
import rut.miit.hotel.repositories.HotelRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelDomainServiceImpl implements HotelDomainService {

    private final HotelRepository hotelRepository;
    private final BookingDomainService BookingDomainService;
    private final ModelMapper modelMapper;

    public HotelDomainServiceImpl(HotelRepository hotelRepository, BookingDomainServiceImpl bookingService, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.BookingDomainService = bookingService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HotelResponseDto> findAvailableHotelsAndRooms(LocalDate startDate, LocalDate endDate, Integer capacity,
                                                              Integer maxPrice, String country, String city, Integer rating) {

        List<Hotel> hotels = hotelRepository.findByCountryAndCityAndRating(country, city, rating);
        List<Hotel> availableHotels = new ArrayList<>();
        for (Hotel hotel : hotels) {
            List<Room> rooms = hotel.getRooms().stream()
                    .filter(room -> BookingDomainService.isRoomAvailable(room, startDate, endDate))
                    .filter(room -> capacity == null || room.getCapacity() >= capacity)
                    .filter(room -> maxPrice == null || room.getPricePerNight().compareTo(maxPrice) <= 0)
                    .toList();

            if (!rooms.isEmpty()) {
                hotel.setRooms(rooms);
                availableHotels.add(hotel);
            }
        }

        return availableHotels.stream().map(e -> modelMapper.map(e, HotelResponseDto.class)).toList();
    }
}
