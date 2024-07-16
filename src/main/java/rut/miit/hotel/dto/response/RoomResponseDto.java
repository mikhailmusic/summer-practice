package rut.miit.hotel.dto.response;


public class RoomResponseDto {
    private Integer id;
    private Integer roomNumber;
    private Byte capacity;
    private Integer pricePerNight;
    private Byte numberOfRooms;
    private String description;
    private String roomTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Byte getCapacity() {
        return capacity;
    }

    public void setCapacity(Byte capacity) {
        this.capacity = capacity;
    }

    public Integer getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Integer pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Byte getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Byte numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
