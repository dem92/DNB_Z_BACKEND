/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.ExceptionHandling;

import no.westerdals.pj3100g15.ServerLogging.WriteLogg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     *  Gets customerNotFoundException and writes it to loggfile.
     * @param e Exception-object
     * @return ResponseEntity
     */
    @ExceptionHandler
    public ResponseEntity handleCustomerNotFoundException(final CustomerNotFoundException e) {
        WriteLogg.writeLogg(e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * Writes down all the internal errors in the spring server to a loggfile.
     * @param exception the exception-object.
     * @return ResponseEntity
     */
    @ExceptionHandler
    public ResponseEntity handleException(final Exception exception) {
        WriteLogg.writeLogg(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage() + " " + exception.toString());
    }
}
