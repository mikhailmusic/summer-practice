package rut.miit.hotel.controller;

import org.springframework.web.bind.annotation.*;
import rut.miit.hotel.dto.request.CustomerRequestDto;
import rut.miit.hotel.dto.response.CustomerResponseDto;
import rut.miit.hotel.service.CustomerService;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<CustomerResponseDto> all() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto one(@PathVariable("id") Integer id) {
        return customerService.findCustomer(id);
    }

    @PostMapping()
    public CustomerResponseDto newCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.register(customerRequestDto);
    }

}
