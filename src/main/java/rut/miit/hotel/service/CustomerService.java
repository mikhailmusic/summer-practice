package rut.miit.hotel.service;


import rut.miit.hotel.dto.request.CustomerRequestDto;
import rut.miit.hotel.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseDto> findAll();
    CustomerResponseDto findCustomer(Integer id);
    CustomerResponseDto register(CustomerRequestDto customerRequestDto);
}
