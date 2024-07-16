package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.*;
import rut.miit.hotel.dto.request.PaymentRequestDto;
import rut.miit.hotel.service.BookingDomainService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final BookingDomainService bookingDomainService;

    public PaymentController(BookingDomainService bookingDomainService) {
        this.bookingDomainService = bookingDomainService;
    }

    @PatchMapping()
    public void pay(@RequestBody PaymentRequestDto paymentRequestDto) {
        bookingDomainService.payPayment(paymentRequestDto);
    }
}
