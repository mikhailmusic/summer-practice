package rut.miit.hotel.repository;

import rut.miit.hotel.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Integer id);

}
