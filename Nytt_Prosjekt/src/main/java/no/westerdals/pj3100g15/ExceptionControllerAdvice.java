package no.westerdals.pj3100g15;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity handleCustomerNotFoundException(final CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleException(final Exception exception) {
        try {
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
            PrintWriter printWriter = new PrintWriter(currentTimestamp.toString() + ".txt", "UTF-8");
            printWriter.write(exception.getMessage() + " " + exception.toString());
            printWriter.write(currentTimestamp.toString());
            printWriter.close();
        } catch (IOException someexception) {
            someexception.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage() + " " + exception.toString());
    }
}
