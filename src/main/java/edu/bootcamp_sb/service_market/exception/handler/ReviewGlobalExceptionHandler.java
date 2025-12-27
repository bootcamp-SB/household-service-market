package edu.bootcamp_sb.service_market.exception.handler;

import edu.bootcamp_sb.service_market.dto.reponse.ErrorResponse;

import edu.bootcamp_sb.service_market.exception.review_exception.ReviewHasBeenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ReviewGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReviewHasBeenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReviewHasNotBeenFound
            (ReviewHasBeenNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("Failed");
        errorResponse.setMessage(ex.getMessage());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
