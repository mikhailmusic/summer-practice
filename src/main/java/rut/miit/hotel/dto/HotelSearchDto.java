package rut.miit.hotel.dto;

import java.time.LocalDate;

public class HotelSearchDto {
    private String country;
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private Byte capacity;
    private Integer maxPrice;
    private Byte rating;

    public HotelSearchDto(String country, String city, LocalDate startDate, LocalDate endDate, Byte capacity, Integer maxPrice, Byte rating) {
        this.country = country;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.maxPrice = maxPrice;
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Byte getCapacity() {
        return capacity;
    }

    public void setCapacity(Byte capacity) {
        this.capacity = capacity;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }
}
