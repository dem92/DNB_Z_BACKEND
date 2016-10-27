package no.westerdals.PJ3100g15;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
