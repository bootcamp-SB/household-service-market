package edu.bootcamp_sb.service_market.exception.client_exceptions;

public class ClientHasBeenNotFoundException extends RuntimeException {

    public ClientHasBeenNotFoundException(String message) {
        super(message);
    }
}
