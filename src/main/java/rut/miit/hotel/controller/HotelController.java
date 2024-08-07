package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rut.miit.hotel.dto.response.HotelResponseDto;
import rut.miit.hotel.service.HotelDomainService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelDomainService hotelService;

    public HotelController(HotelDomainService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/available")
    public List<HotelResponseDto> availableHotelsAndRooms(
            @RequestParam String country, @RequestParam String city,
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
            @RequestParam(required = false) Byte capacity, @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Byte rating) {

        return hotelService
                .findAvailableHotelsAndRooms(startDate, endDate, capacity, maxPrice, country, city, rating);

    }
}
