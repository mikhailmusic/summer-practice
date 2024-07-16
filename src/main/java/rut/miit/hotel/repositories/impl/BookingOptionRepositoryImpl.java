package rut.miit.hotel.repositories.impl;

import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.BookingOption;
import rut.miit.hotel.repositories.BookingOptionRepository;

@Repository
public class BookingOptionRepositoryImpl extends BaseRepository<BookingOption, Integer> implements BookingOptionRepository {

    public BookingOptionRepositoryImpl() {
        super(BookingOption.class);
    }

}
