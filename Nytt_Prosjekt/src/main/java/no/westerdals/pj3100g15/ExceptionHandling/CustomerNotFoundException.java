package no.westerdals.pj3100g15.ExceptionHandling;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
