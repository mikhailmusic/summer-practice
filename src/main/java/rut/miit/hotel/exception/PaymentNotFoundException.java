package rut.miit.hotel.exception;


public class PaymentNotFoundException extends EntityNotFoundException {
    public PaymentNotFoundException(String message) {
        super(message);
    }

    public PaymentNotFoundException() {
        super("Payment not found");
    }

    public PaymentNotFoundException(Integer id) {
        super("Payment with id " + id + " not found");
    }
}