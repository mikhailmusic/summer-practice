package rut.miit.hotel.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "options")
public class Option extends BaseEntity {
    private String name;
    private List<HotelOption> hotelOptions;
    private List<BookingOption> bookingOptions;

    public Option(String name, List<HotelOption> hotelOptions, List<BookingOption> bookingOptions) {
        this.name = name;
        this.hotelOptions = hotelOptions;
        this.bookingOptions = bookingOptions;
    }

    protected Option() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    public List<HotelOption> getHotelOptions() {
        return hotelOptions;
    }

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    public List<BookingOption> getBookingOptions() {
        return bookingOptions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotelOptions(List<HotelOption> hotelOptions) {
        this.hotelOptions = hotelOptions;
    }

    public void setBookingOptions(List<BookingOption> bookingOptions) {
        this.bookingOptions = bookingOptions;
    }
}
