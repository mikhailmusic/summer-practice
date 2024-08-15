package rut.miit.hotel.dto;


public class BookingOptionDto {
    private Integer hotelOptionId;
    private Byte count;

    public Integer getHotelOptionId() {
        return hotelOptionId;
    }

    public void setHotelOptionId(Integer hotelOptionId) {
        this.hotelOptionId = hotelOptionId;
    }

    public Byte getCount() {
        return count;
    }

    public void setCount(Byte count) {
        this.count = count;
    }
}
