package rut.miit.hotel.repositories;

import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.domain.status.PaymentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Optional<Booking> findById(Integer id);
    Booking save(Booking booking);
    List<Booking> findOverlappingBookings(Customer customer, LocalDate startDate, LocalDate endDate);
    List<Booking> findBookingsWithFailedPayments(Customer customer, List<PaymentStatus> statuses);
    List<Booking> findBookingsByRoomAndDateRange(Room room, LocalDate startDate, LocalDate endDate);

}
