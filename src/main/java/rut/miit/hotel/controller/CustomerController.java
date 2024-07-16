package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.*;
import rut.miit.hotel.dto.request.CustomerRequestDto;
import rut.miit.hotel.dto.response.CustomerResponseDto;
import rut.miit.hotel.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public Iterable<CustomerResponseDto> getAllUsers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getById(@PathVariable() int id) {
        return customerService.findCustomer(id);
    }

    @PostMapping()
    public CustomerResponseDto create(@RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.register(customerRequestDto);
    }

}
