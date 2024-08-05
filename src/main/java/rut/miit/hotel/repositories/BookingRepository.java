package rut.miit.hotel.repositories;

import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.domain.status.BookingStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Optional<Booking> findById(Integer id);
    Booking save(Booking booking);
    Booking update(Booking booking);
    List<Booking> findByCustomerDateRangeStatuses(Customer customer, LocalDate startDate, LocalDate endDate, List<BookingStatus> statuses);
    List<Booking> findByRoomAndDateRange(Room room, LocalDate startDate, LocalDate endDate);

}
