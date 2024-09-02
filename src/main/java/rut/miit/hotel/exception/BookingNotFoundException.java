package rut.miit.hotel.exception;


public class BookingNotFoundException extends EntityNotFoundException {
    public BookingNotFoundException(String message) {
        super(message);
    }

    public BookingNotFoundException() {
        super("Booking not found");
    }

    public BookingNotFoundException(Integer id) {
        super("Booking with id " + id + " not found");
    }
}