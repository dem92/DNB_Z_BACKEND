package no.westerdals.pj3100g15;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity handleCustomerNotFoundException(final CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleException(final Exception exception) {
        try{
        PrintWriter printWriter = new PrintWriter("errormessages.txt", "UTF-8");
        printWriter.write(exception.getMessage()+" "+exception.toString());
        printWriter.close();
        }catch (IOException ioexception){
            ioexception.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage()+" "+exception.toString());
    }
}
