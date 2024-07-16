package rut.miit.hotel.controller;


import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<HotelOptionResponseDto>> recommendOptions(@PathVariable Integer hotelId, @PathVariable Integer customerId) {
        List<HotelOptionResponseDto> recommendedOptions = hotelOptionService.recommendOptions(customerId, hotelId);
        if (recommendedOptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(recommendedOptions);
        }
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<List<HotelOptionResponseDto>> hotelOptions(@PathVariable Integer id) {
        List<HotelOptionResponseDto> hotelOptions = hotelOptionService.findAll(id);
        if (hotelOptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(hotelOptions);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<List<HotelOptionResponseDto>> paidHotelOptions(@PathVariable Integer id) {
        List<HotelOptionResponseDto> hotelOptions = hotelOptionService.findByCustomer(id);
        if (hotelOptions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(hotelOptions);
        }
    }
}
