package rut.miit.hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "booking_services")
public class BookingService extends BaseEntity {
    private Booking booking;
    private Service service;
    private Byte count;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    public Booking getBooking() {
        return booking;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    public Service getService() {
        return service;
    }

    @Column(name = "count")
    public Byte getCount() {
        return count;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setCount(Byte count) {
        this.count = count;
    }
}
