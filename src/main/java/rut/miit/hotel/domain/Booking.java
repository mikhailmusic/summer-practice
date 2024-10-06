package rut.miit.hotel.domain;

import jakarta.persistence.*;
import rut.miit.hotel.domain.status.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {
    private LocalDateTime createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;
    private Room room;
    private Customer customer;
    private List<Payment> payments;
    private List<BookingOption> bookingOptions;

    public Booking(LocalDate startDate, LocalDate endDate, Room room, Customer customer) {
        validateDates(startDate, endDate);
        this.createdAt = LocalDateTime.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = BookingStatus.CREATED;
        this.room = room;
        this.customer = customer;
        this.payments = new ArrayList<>();
        this.bookingOptions = new ArrayList<>();
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

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public BookingStatus getStatus() {
        return status;
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

    @OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
    public List<Payment> getPayments() {
        return payments;
    }

    @OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
    public List<BookingOption> getBookingOptions() {
        return bookingOptions;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStartDate(LocalDate startDate) {
        if (this.endDate != null) {
            validateDates(startDate, this.endDate);
        }
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (this.startDate != null) {
            validateDates(this.startDate, endDate);
        }
        this.endDate = endDate;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
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
        if (bookingOptions != null && bookingOptions.size() > 10) {
            throw new IllegalArgumentException("Exceeded maximum number of BookingOptions: 10");
        }
        this.bookingOptions = bookingOptions;
    }


    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("startDate cannot be after endDate");
        }
        LocalDate today = LocalDate.now();
        if (startDate.isBefore(today) || endDate.isAfter(today.plusYears(1))) {
            throw new IllegalArgumentException("Booking period must be within one year");
        }
        if (ChronoUnit.DAYS.between(startDate, endDate) > 90) {
            throw new IllegalArgumentException("Difference between startDate and endDate cannot be more than 90 days");
        }
    }

    public boolean checkValid(){
        return status.equals(BookingStatus.COMPLETED) ||
                status.equals(BookingStatus.CREATED) && ChronoUnit.MINUTES.between(createdAt, LocalDateTime.now()) < 60;
    }

    public void addPayment(Payment payment) {
        if (payment != null && status != BookingStatus.CANCELLED) {
            payments.add(payment);
        } else throw new IllegalStateException("Cannot add payment to a cancelled booking");

    }

    public void addBookingOption(BookingOption option) {
        if (option != null && status == BookingStatus.CREATED && bookingOptions.size() + 1 <= 10) {
            bookingOptions.add(option);
        }
        else throw new IllegalStateException("Cannot add option to booking");

    }

}
