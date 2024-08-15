package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.*;
import rut.miit.hotel.dto.BookingDto;
import rut.miit.hotel.service.BookingDomainService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingDomainService bookingService;

    public BookingController(BookingDomainService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.createBooking(bookingDto);

    }

    @DeleteMapping("/{id}")
    public BookingDto cancelBooking(@PathVariable Integer id) {
        return bookingService.cancelBooking(id);
    }

}
