package rut.miit.hotel.entities.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HotelServiceKey implements Serializable {
    private Integer hotelId;
    private Integer serviceId;

    public Integer getHotelId() {
        return hotelId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelServiceKey that = (HotelServiceKey) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, serviceId);
    }
}
