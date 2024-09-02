package rut.miit.hotel.exception;


public class RoomNotFoundException extends EntityNotFoundException {
    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException() {
        super("Room not found");
    }

    public RoomNotFoundException(Integer id) {
        super("Room with id " + id + " not found");
    }
}