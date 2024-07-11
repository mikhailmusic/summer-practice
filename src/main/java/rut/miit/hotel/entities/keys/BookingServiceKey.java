package rut.miit.hotel.entities.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookingServiceKey implements Serializable {
    private Integer bookingId;
    private Integer serviceId;

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingServiceKey that = (BookingServiceKey) o;
        return Objects.equals(bookingId, that.bookingId) && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, serviceId);
    }
}
