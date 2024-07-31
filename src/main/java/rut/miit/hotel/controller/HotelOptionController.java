package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rut.miit.hotel.dto.response.HotelOptionResponseDto;
import rut.miit.hotel.service.HotelOptionDomainService;

import java.util.List;

@RestController
@RequestMapping("/options")
public class HotelOptionController {
    private final HotelOptionDomainService hotelOptionService;

    public HotelOptionController(HotelOptionDomainService hotelOptionService) {
        this.hotelOptionService = hotelOptionService;
    }

    @GetMapping("/hotels/{hotelId}/customers/{customerId}")
    public List<HotelOptionResponseDto> recommendOptions(@PathVariable Integer hotelId, @PathVariable Integer customerId) {
        return hotelOptionService.recommendOptions(customerId, hotelId);
    }

    @GetMapping("/hotels/{id}")
    public List<HotelOptionResponseDto> hotelOptions(@PathVariable Integer id) {
        return hotelOptionService.findAll(id);
    }

    @GetMapping("/customers/{id}")
    public List<HotelOptionResponseDto> paidHotelOptions(@PathVariable Integer id) {
        return hotelOptionService.findByCustomer(id);
    }
}
