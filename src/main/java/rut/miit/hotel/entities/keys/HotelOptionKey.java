package rut.miit.hotel.entities.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HotelOptionKey implements Serializable {
    private Integer hotelId;
    private Integer optionId;


    public Integer getHotelId() {
        return hotelId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelOptionKey that = (HotelOptionKey) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(optionId, that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, optionId);
    }
}
