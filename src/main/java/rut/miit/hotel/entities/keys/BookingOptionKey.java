package rut.miit.hotel.entities.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookingOptionKey implements Serializable {
    private Integer bookingId;
    private Integer optionId;

    public Integer getBookingId() {
        return bookingId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingOptionKey that = (BookingOptionKey) o;
        return Objects.equals(bookingId, that.bookingId) && Objects.equals(optionId, that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, optionId);
    }
}
