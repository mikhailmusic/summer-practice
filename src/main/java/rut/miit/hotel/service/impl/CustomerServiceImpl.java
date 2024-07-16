package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.dto.request.CustomerRequestDto;
import rut.miit.hotel.dto.response.CustomerResponseDto;
import rut.miit.hotel.repositories.CustomerRepository;
import rut.miit.hotel.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return customerRepository.findAll().stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).toList();
    }

    @Override
    public CustomerResponseDto findCustomer(Integer id) {
        return modelMapper.map(customerRepository.findById(id).stream(), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto register(CustomerRequestDto customerRequestDto) {
        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

}
