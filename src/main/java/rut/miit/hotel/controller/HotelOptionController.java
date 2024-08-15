package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rut.miit.hotel.dto.HotelOptionDto;
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
    public List<HotelOptionDto> recommendOptions(@PathVariable Integer hotelId, @PathVariable Integer customerId) {
        return hotelOptionService.getRecommendOptions(customerId, hotelId);
    }

    @GetMapping("/hotels/{id}")
    public List<HotelOptionDto> hotelOptions(@PathVariable Integer id) {
        return hotelOptionService.findByHotel(id);
    }

    @GetMapping("/customers/{id}")
    public List<HotelOptionDto> paidHotelOptions(@PathVariable Integer id) {
        return hotelOptionService.findByCustomer(id);
    }
}
