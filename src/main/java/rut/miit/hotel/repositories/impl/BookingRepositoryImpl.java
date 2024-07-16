package rut.miit.hotel.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.domain.Customer;
import rut.miit.hotel.domain.Room;
import rut.miit.hotel.domain.status.BookingStatus;
import rut.miit.hotel.domain.status.PaymentStatus;
import rut.miit.hotel.repositories.BookingRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingRepositoryImpl extends BaseRepository<Booking, Integer> implements BookingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public BookingRepositoryImpl() {
        super(Booking.class);
    }

    // Поиск бронирований клиента в определенном диапазоне дат
    public List<Booking> findByCustomerDateRangeStatuses(Customer customer, LocalDate startDate, LocalDate endDate, List<BookingStatus> statuses){
        return entityManager.createQuery("SELECT b FROM Booking b WHERE b.customer = :customer AND b.startDate <= :endDate AND b.endDate >= :startDate AND b.bookingStatus IN :statuses", Booking.class)
                .setParameter("customer", customer).setParameter("startDate", startDate).setParameter("endDate", endDate)
                .setParameter("statuses", statuses)
                .getResultList();
    }

    // новые бронирования с определенными статусами
    public List<Booking> findBookingsWithFailedPayments(Customer customer, List<PaymentStatus> statuses) {
        return entityManager.createQuery("SELECT b FROM Booking b JOIN b.payments p WHERE p.status IN :statuses AND b.customer = :customer ORDER BY b.createdAt DESC", Booking.class)
                .setParameter("customer", customer).setParameter("statuses", statuses)
                .getResultList();
    }

    // Получение бронирований комнаты на указанные даты
    public List<Booking> findBookingsByRoomAndDateRange(Room room, LocalDate startDate, LocalDate endDate){
        return entityManager.createQuery("SELECT b FROM Booking b WHERE b.room = :room AND b.startDate <= :endDate AND b.endDate >= :startDate", Booking.class)
                .setParameter("room", room).setParameter("startDate", startDate).setParameter("endDate", endDate)
                .getResultList();
    }

}
