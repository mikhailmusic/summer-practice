package rut.miit.hotel.entities;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity {
    private String name;
    private Byte rating;
    private String phoneNumber;
    private String email;
    private String description;
    private String country;
    private String city;
    private String street;
    private String buildingNumber;  // Example: 42/1, 12A
    private String postcode;        // Example: EC2M7QH (London)
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private List<Room> rooms;
    private List<HotelOption> hotelOptions;

    public Hotel(String name, Byte rating, String phoneNumber, String email, String description, String country, String city, String street, String buildingNumber, String postcode, LocalTime checkInTime, LocalTime checkOutTime, List<Room> rooms, List<HotelOption> hotelOptions) {
        this.name = name;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.description = description;
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postcode = postcode;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.rooms = rooms;
        this.hotelOptions = hotelOptions;
    }

    protected Hotel() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "rating")
    public Byte getRating() {
        return rating;
    }

    @Column(name = "phone_number",length = 20, nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "description", length = 1500)
    public String getDescription() {
        return description;
    }

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    @Column(name = "street", nullable = false)
    public String getStreet() {
        return street;
    }

    @Column(name = "building_number",length = 10, nullable = false)
    public String getBuildingNumber() {
        return buildingNumber;
    }

    @Column(name = "postcode",length = 10, nullable = false)
    public String getPostcode() {
        return postcode;
    }

    @Column(name = "check_in_time", nullable = false)
    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    @Column(name = "check_out_time", nullable = false)
    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    public List<Room> getRooms() {
        return rooms;
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    public List<HotelOption> getHotelOptions() {
        return hotelOptions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setHotelOptions(List<HotelOption> hotelOptions) {
        this.hotelOptions = hotelOptions;
    }
}
