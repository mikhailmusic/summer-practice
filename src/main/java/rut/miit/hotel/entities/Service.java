package rut.miit.hotel.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "services")
public class Service extends BaseEntity {
    private String name;
    private List<HotelService> hotelServices;
    private List<BookingService> bookingServices;

    public Service(String name, List<HotelService> hotelServices, List<BookingService> bookingServices) {
        this.name = name;
        this.hotelServices = hotelServices;
        this.bookingServices = bookingServices;
    }

    protected Service() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    public List<HotelService> getHotelServices() {
        return hotelServices;
    }

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    public List<BookingService> getBookingServices() {
        return bookingServices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotelServices(List<HotelService> hotelServices) {
        this.hotelServices = hotelServices;
    }

    public void setBookingServices(List<BookingService> bookingServices) {
        this.bookingServices = bookingServices;
    }
}
