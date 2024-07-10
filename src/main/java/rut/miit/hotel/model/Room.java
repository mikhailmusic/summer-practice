package rut.miit.hotel.model;

import jakarta.persistence.*;
import rut.miit.hotel.model.enums.RoomType;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {
    private Integer roomNumber;
    private Byte capacity;
    private Integer pricePerNight;
    private RoomType type;
    private Byte numberOfRooms;
    private String description;

    // This flag can be set to false in case the room is out of order (e.g., due to maintenance issues or damages)
    private boolean isFunctional = true;
    private Hotel hotel;
    private List<Booking> bookings;

    @Column(name = "room_number", nullable = false)
    public Integer getRoomNumber() {
        return roomNumber;
    }

    @Column(name = "capacity", nullable = false)
    public Byte getCapacity() {
        return capacity;
    }

    @Column(name = "price_per_night", nullable = false)
    public Integer getPricePerNight() {
        return pricePerNight;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public RoomType getType() {
        return type;
    }

    @Column(name = "number_of_rooms")
    public Byte getNumberOfRooms() {
        return numberOfRooms;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return description;
    }

    @Column(name = "functional", nullable = false)
    public boolean isFunctional() {
        return isFunctional;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    public Hotel getHotel() {
        return hotel;
    }

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCapacity(Byte capacity) {
        this.capacity = capacity;
    }

    public void setPricePerNight(Integer pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setNumberOfRooms(Byte numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
