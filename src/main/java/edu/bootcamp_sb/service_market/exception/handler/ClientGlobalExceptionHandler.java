package edu.bootcamp_sb.service_market.exception.handler;

import edu.bootcamp_sb.service_market.dto.reponse.ClientErrorResponse;
import edu.bootcamp_sb.service_market.dto.reponse.ErrorResponse;
import edu.bootcamp_sb.service_market.exception.clientExceptions.ClientHasBeenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClientGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ClientHasBeenNotFoundException.class)
    public ResponseEntity<ClientErrorResponse>clientHasBeenNotFound
            (ClientHasBeenNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ClientErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build());

    }
}
