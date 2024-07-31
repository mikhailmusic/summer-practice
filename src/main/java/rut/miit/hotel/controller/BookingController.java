package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.*;
import rut.miit.hotel.dto.request.BookingRequestDto;
import rut.miit.hotel.dto.response.BookingResponseDto;
import rut.miit.hotel.service.BookingDomainService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingDomainService bookingService;

    public BookingController(BookingDomainService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("")
    public BookingResponseDto createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.createBooking(bookingRequestDto);

    }

    @DeleteMapping("/{id}")
    public BookingResponseDto cancelBooking(@PathVariable Integer id) {
        return bookingService.cancelBooking(id);
    }

}
