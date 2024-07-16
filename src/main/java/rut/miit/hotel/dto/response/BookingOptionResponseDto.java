package rut.miit.hotel.dto.response;


public class BookingOptionResponseDto {
    private Integer id;
    private Integer hotelOptionId;
    private Byte count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
