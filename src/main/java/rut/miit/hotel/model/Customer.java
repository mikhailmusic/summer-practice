package rut.miit.hotel.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean gender;         // 1 - male, 0 - female
    private LocalDate birthDate;
    private List<Booking> bookings;

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "phone_number", length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "gender", nullable = false)
    public boolean getGender() {
        return gender;
    }

    @Column(name = "birth_date", nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}