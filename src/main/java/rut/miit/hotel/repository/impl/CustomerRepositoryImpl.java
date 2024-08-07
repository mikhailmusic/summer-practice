package rut.miit.hotel.repository.impl;

import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl extends BaseRepository<Customer, Integer> implements CustomerRepository {

    public CustomerRepositoryImpl() {
        super(Customer.class);
    }

}
