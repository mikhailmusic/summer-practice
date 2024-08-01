package rut.miit.hotel.domain;

import jakarta.persistence.*;
import rut.miit.hotel.domain.status.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
    private LocalDateTime createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus bookingStatus;
    private Room room;
    private Customer customer;
    private List<Payment> payments;
    private List<BookingOption> bookingOptions;

    public static final int MAX_BOOKING_DAYS = 90;
    public static final int MAX_COUNT_OPTIONS = 10;

    public Booking(LocalDate startDate, LocalDate endDate, Room room, Customer customer) {
        validateDates(startDate, endDate);
        this.createdAt = LocalDateTime.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingStatus = BookingStatus.CREATED;
        this.room = room;
        this.customer = customer;
    }

    protected Booking() {
    }

    @Column(name = "created_at",updatable = false, nullable = false)
    public LocalDateTime getCreatedAt() {
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

    @Column(name = "booking_status", nullable = false)
    @Enumerated(EnumType.STRING)
    public BookingStatus getBookingStatus() {
        return bookingStatus;
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
    public List<BookingOption> getBookingOptions() {
        return bookingOptions;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStartDate(LocalDate startDate) {
        validateDates(startDate, this.endDate);
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        validateDates(this.startDate, endDate);
        this.endDate = endDate;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
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

    public void setBookingOptions(List<BookingOption> bookingOptions) {
        validateBookingOptions(bookingOptions);
        this.bookingOptions = bookingOptions;
    }


    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) return;
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate cannot be after endDate");
        }

        if (ChronoUnit.DAYS.between(startDate, endDate) > MAX_BOOKING_DAYS) {
            throw new IllegalArgumentException("Difference between startDate and endDate cannot be more than " + MAX_BOOKING_DAYS +  " days");
        }
    }

    private void validateBookingOptions(List<BookingOption> bookingOptions) {
        if (bookingOptions.size() > MAX_COUNT_OPTIONS) {
            throw new IllegalArgumentException("Exceeded maximum number of BookingOptions (" + MAX_COUNT_OPTIONS + ")");
        }
    }
}
