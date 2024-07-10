package rut.miit.hotel.model;

import jakarta.persistence.*;

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
    private List<Room> rooms;
    private List<HotelService> hotelServices;

    @Column(name = "name", nullable = false)
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

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    public List<Room> getRooms() {
        return rooms;
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    public List<HotelService> getHotelServices() {
        return hotelServices;
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

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setHotelServices(List<HotelService> hotelServices) {
        this.hotelServices = hotelServices;
    }
}
