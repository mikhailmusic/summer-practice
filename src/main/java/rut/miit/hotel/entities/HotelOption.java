package rut.miit.hotel.entities;

import jakarta.persistence.*;
import rut.miit.hotel.entities.keys.HotelOptionKey;

@Entity
@Table(name = "hotel_options")
public class HotelOption {
    private HotelOptionKey id;
    private Integer price;
    private String description;
    private Hotel hotel;
    private Option option;

    public HotelOption(HotelOptionKey id, Integer price, String description, Hotel hotel, Option option) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.hotel = hotel;
        this.option = option;
    }

    protected HotelOption() {
    }

    @EmbeddedId
    public HotelOptionKey getId() {
        return id;
    }

    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    @Column(name = "description", length = 500)
    public String getDescription() {
        return description;
    }

    @MapsId("hotelId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    public Hotel getHotel() {
        return hotel;
    }

    @MapsId("optionId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    public Option getOption() {
        return option;
    }

    public void setId(HotelOptionKey id) {
        this.id = id;
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
}
