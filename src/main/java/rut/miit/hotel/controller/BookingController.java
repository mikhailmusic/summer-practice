package rut.miit.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rut.miit.hotel.dto.request.BookingRequestDto;
import rut.miit.hotel.dto.response.BookingResponseDto;
import rut.miit.hotel.dto.response.PaymentResponseDto;
import rut.miit.hotel.service.BookingDomainService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingDomainService bookingService;

    public BookingController(BookingDomainService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<BookingResponseDto> createBooking(BookingRequestDto bookingRequestDto) {
        return ResponseEntity.ok(bookingService.createBooking(bookingRequestDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> cancelBooking(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

}
