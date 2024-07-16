package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.domain.status.PaymentStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends GeneralRepository<Booking, Integer> {
    // Поиск бронирований клиента в определенном диапазоне дат
    @Query("SELECT b FROM Booking b WHERE b.customer = :customer AND b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Booking> findOverlappingBookings(@Param("customer") Customer customer, @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);

    // новые бронирования с определенными статусами
    @Query("SELECT b FROM Booking b JOIN b.payments p WHERE p.status IN :statuses " +
            "AND b.customer = :customer ORDER BY b.createdAt DESC")
    List<Booking> findBookingsWithFailedPayments(@Param("customer") Customer customer, @Param("statuses") List<PaymentStatus> statuses);

    // Получение бронирований комнаты на указанные даты
    @Query("SELECT b FROM Booking b WHERE b.room = :room AND b.startDate <= :endDate AND b.endDate >= :startDate")
    List<Booking> findBookingsByRoomAndDateRange( @Param("room") Room room, @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate
    );

}
