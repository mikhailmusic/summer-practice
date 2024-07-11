package rut.miit.hotel.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hotel_options")
public class HotelOption extends BaseEntity {
    private String fullName;
    private Integer price;
    private String description;
    private Hotel hotel;
    private Option option;
    private List<BookingOption> bookingOptions;

    public HotelOption(String fullName, Integer price, String description, Hotel hotel, Option option, List<BookingOption> bookingOptions) {
        this.fullName = fullName;
        this.price = price;
        this.description = description;
        this.hotel = hotel;
        this.option = option;
        this.bookingOptions = bookingOptions;
    }

    protected HotelOption() {
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    @Column(name = "description", length = 500)
    public String getDescription() {
        return description;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    public Hotel getHotel() {
        return hotel;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    public Option getOption() {
        return option;
    }

    @OneToMany(mappedBy = "hotelOption")
    public List<BookingOption> getBookingOptions() {
        return bookingOptions;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setBookingOptions(List<BookingOption> bookingOptions) {
        this.bookingOptions = bookingOptions;
    }
}
