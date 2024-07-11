package rut.miit.hotel.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
    private OffsetDateTime createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isBooked;
    private Room room;
    private Customer customer;
    private List<Payment> payments;
    private List<BookingService> bookingServices;

    public Booking(OffsetDateTime createdAt, LocalDate startDate, LocalDate endDate, Room room, Customer customer, List<Payment> payments, List<BookingService> bookingServices) {
        this.createdAt = OffsetDateTime.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.isBooked = false;
        this.room = room;
        this.customer = customer;
        this.payments = payments;
        this.bookingServices = bookingServices;
    }

    protected Booking() {
    }

    @Column(name = "created_at",updatable = false, nullable = false)
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @Column(name = "start_date", nullable = false)
    public LocalDate getStartDate() {
        return startDate;
    }

    @Column(name = "end_date", nullable = false)
    public LocalDate getEndDate() {
        return endDate;
    }

    @Column(name = "booked")
    public boolean isBooked() {
        return isBooked;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    public Room getRoom() {
        return room;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    public List<Payment> getPayments() {
        return payments;
    }

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    public List<BookingService> getBookingServices() {
        return bookingServices;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void setBookingServices(List<BookingService> bookingServices) {
        this.bookingServices = bookingServices;
    }
}
