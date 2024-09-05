package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Hotel;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.dto.HotelDto;
import rut.miit.hotel.dto.HotelSearchDto;
import rut.miit.hotel.dto.RoomDto;
import rut.miit.hotel.repository.BookingRepository;
import rut.miit.hotel.service.HotelDomainService;
import rut.miit.hotel.repository.HotelRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelDomainServiceImpl implements HotelDomainService {

    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public HotelDomainServiceImpl(HotelRepository hotelRepository, BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
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
                    .filter(room -> roomAvailableForBooking(room, startDate, endDate))
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
    boolean roomAvailableForBooking(Room room, LocalDate startDate, LocalDate endDate) {
        if (!room.isFunctional()) return false;
        List<Booking> bookings = bookingRepository.findByRoomAndDateRange(room, startDate, endDate);
        return bookings.stream().noneMatch(Booking::checkValid);
    }
}
