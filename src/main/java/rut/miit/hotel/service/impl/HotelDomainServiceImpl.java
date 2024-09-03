package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rut.miit.hotel.domain.Hotel;
import rut.miit.hotel.dto.HotelDto;
import rut.miit.hotel.dto.HotelSearchDto;
import rut.miit.hotel.dto.RoomDto;
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
    public List<HotelDto> findAvailableHotelsAndRooms(HotelSearchDto hotelSearchDto) {
        List<Hotel> hotels = hotelRepository.findByAttributes(hotelSearchDto.getCountry(), hotelSearchDto.getCity(), hotelSearchDto.getRating());

        List<HotelDto> availableHotelsDto = new ArrayList<>();
        Byte capacity = hotelSearchDto.getCapacity();
        Integer maxPrice = hotelSearchDto.getMaxPrice();
        LocalDate startDate = hotelSearchDto.getStartDate();
        LocalDate endDate = hotelSearchDto.getEndDate();
        for (Hotel hotel : hotels) {
            List<RoomDto> availableRooms = hotel.getRooms().stream()
                    .filter(room -> bookingDomainService.isRoomAvailable(room, startDate, endDate))
                    .filter(room -> capacity == null || room.getCapacity() >= capacity)
                    .filter(room -> maxPrice == null || room.getPricePerNight().compareTo(maxPrice) <= 0)
                    .map(room -> modelMapper.map(room, RoomDto.class))
                    .toList();

            if (!availableRooms.isEmpty()) {
                HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
                hotelDto.setRooms(availableRooms);
                availableHotelsDto.add(hotelDto);
            }
        }

        return availableHotelsDto;
    }
}
