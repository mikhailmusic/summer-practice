package rut.miit.hotel.exception;


public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException() {
        super("Customer not found");
    }

    public CustomerNotFoundException(Integer id) {
        super("Customer with id " + id + " not found");
    }
}