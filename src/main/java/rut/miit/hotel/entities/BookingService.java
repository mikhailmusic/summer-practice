package rut.miit.hotel.entities;

import jakarta.persistence.*;
import rut.miit.hotel.entities.keys.BookingServiceKey;

@Entity
@Table(name = "booking_services")
public class BookingService {
    private BookingServiceKey id;
    private Booking booking;
    private Service service;
    private Byte count;

    public BookingService(BookingServiceKey id, Booking booking, Service service, Byte count) {
        this.id = id;
        this.booking = booking;
        this.service = service;
        this.count = count;
    }

    protected BookingService() {
    }

    @EmbeddedId
    public BookingServiceKey getId() {
        return id;
    }

    @MapsId("bookingId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    public Booking getBooking() {
        return booking;
    }

    @MapsId("serviceId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    public Service getService() {
        return service;
    }

    @Column(name = "count")
    public Byte getCount() {
        return count;
    }

    public void setId(BookingServiceKey id) {
        this.id = id;
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
