package rut.miit.hotel.repositories;


import rut.miit.hotel.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Integer id);
    Customer save(Customer customer);
    List<Customer> findAll();
}
