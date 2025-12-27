package edu.bootcamp_sb.service_market.exception.handler;

import edu.bootcamp_sb.service_market.dto.reponse.ErrorResponse;
import edu.bootcamp_sb.service_market.exception.booking_exception.BookingHasNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class BookingGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookingHasNotFoundException.class)
    public ResponseEntity<ErrorResponse>bookingHasNotFound(BookingHasNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus("Failed");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
