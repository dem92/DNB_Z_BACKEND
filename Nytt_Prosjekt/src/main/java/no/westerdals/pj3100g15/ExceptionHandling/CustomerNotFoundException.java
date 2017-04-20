package no.westerdals.pj3100g15.ExceptionHandling;

public class CustomerNotFoundException extends RuntimeException {
    /**
     * Gets the errormessage when a customer is not found in the database.
     *
     * @param message is the errormessage.
     */
    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
