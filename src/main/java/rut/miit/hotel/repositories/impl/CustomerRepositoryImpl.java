package rut.miit.hotel.repositories.impl;

import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.repositories.CustomerRepository;

@Repository
public class CustomerRepositoryImpl extends BaseRepository<Customer, Integer> implements CustomerRepository {

    public CustomerRepositoryImpl() {
        super(Customer.class);
    }

}
