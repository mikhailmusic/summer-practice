package rut.miit.hotel.exception;


public class HotelOptionNotFoundException extends EntityNotFoundException {
    public HotelOptionNotFoundException(String message) {
        super(message);
    }

    public HotelOptionNotFoundException() {
        super("Hotel option not found");
    }

    public HotelOptionNotFoundException(Integer id) {
        super("HotelOption with id " + id + " not found");
    }
}