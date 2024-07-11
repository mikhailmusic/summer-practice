package rut.miit.hotel.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "booking_options")
public class BookingOption extends BaseEntity {
    private Booking booking;
    private HotelOption hotelOption;
    private Byte count;

    public BookingOption(Booking booking, HotelOption hotelOption, Byte count) {
        this.booking = booking;
        this.hotelOption = hotelOption;
        this.count = count;
    }

    protected BookingOption() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    public Booking getBooking() {
        return booking;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_option_id", nullable = false)
    public HotelOption getHotelOption() {
        return hotelOption;
    }

    @Column(name = "count")
    public Byte getCount() {
        return count;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setHotelOption(HotelOption hotelOption) {
        this.hotelOption = hotelOption;
    }

    public void setCount(Byte count) {
        this.count = count;
    }
}
