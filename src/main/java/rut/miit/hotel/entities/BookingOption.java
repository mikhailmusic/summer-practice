package rut.miit.hotel.entities;

import jakarta.persistence.*;
import rut.miit.hotel.entities.keys.BookingOptionKey;

@Entity
@Table(name = "booking_options")
public class BookingOption {
    private BookingOptionKey id;
    private Booking booking;
    private Option option;
    private Byte count;

    public BookingOption(BookingOptionKey id, Booking booking, Option option, Byte count) {
        this.id = id;
        this.booking = booking;
        this.option = option;
        this.count = count;
    }

    protected BookingOption() {
    }

    @EmbeddedId
    public BookingOptionKey getId() {
        return id;
    }

    @MapsId("bookingId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    public Booking getBooking() {
        return booking;
    }

    @MapsId("optionId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    public Option getOption() {
        return option;
    }

    @Column(name = "count")
    public Byte getCount() {
        return count;
    }

    public void setId(BookingOptionKey id) {
        this.id = id;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setCount(Byte count) {
        this.count = count;
    }
}
