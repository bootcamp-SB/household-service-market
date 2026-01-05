package edu.bootcamp_sb.service_market.exception.handler;

import edu.bootcamp_sb.service_market.dto.reponse.ErrorResponse;
import edu.bootcamp_sb.service_market.exception.keycloak_user.FailedToCreateUserException;
import edu.bootcamp_sb.service_market.exception.keycloak_user.FailedToSendEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class KeycloakUserGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FailedToCreateUserException.class)
    public ResponseEntity<ErrorResponse>failedToCreateUser(FailedToCreateUserException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("Failed TO Create");
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
    @ExceptionHandler(FailedToSendEmailException.class)
    public ResponseEntity<ErrorResponse>failedToSendEmail(FailedToSendEmailException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("Failed to send email");
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


}
