package rut.miit.hotel.entities;

import jakarta.persistence.*;
import rut.miit.hotel.entities.keys.HotelServiceKey;

@Entity
@Table(name = "hotel_services")
public class HotelService {
    private HotelServiceKey id;
    private Integer price;
    private String description;
    private Hotel hotel;
    private Service service;

    public HotelService(HotelServiceKey id, Integer price, String description, Hotel hotel, Service service) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.hotel = hotel;
        this.service = service;
    }

    protected HotelService() {
    }

    @EmbeddedId
    public HotelServiceKey getId() {
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

    @MapsId("serviceId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    public Service getService() {
        return service;
    }

    public void setId(HotelServiceKey id) {
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

    public void setService(Service service) {
        this.service = service;
    }
}
