package rut.miit.hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel_services")
public class HotelService extends BaseEntity {
    private Integer price;
    private String description;
    private Hotel hotel;
    private Service service;

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
    @JoinColumn(name = "service_id", nullable = false)
    public Service getService() {
        return service;
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
