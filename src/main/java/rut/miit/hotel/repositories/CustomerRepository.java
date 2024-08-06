package rut.miit.hotel.repositories;

import rut.miit.hotel.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Integer id);

}
