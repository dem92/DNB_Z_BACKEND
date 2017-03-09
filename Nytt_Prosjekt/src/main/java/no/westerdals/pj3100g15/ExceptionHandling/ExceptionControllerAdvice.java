package no.westerdals.pj3100g15.ExceptionHandling;

import no.westerdals.pj3100g15.ServerLogging.WriteLogg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity handleCustomerNotFoundException(final CustomerNotFoundException e) {
        WriteLogg.writeLogg(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleException(final Exception exception) {

        WriteLogg.writeLogg(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage() + " " + exception.toString());
    }
}
