package edu.bootcamp_sb.service_market.exception.client_exceptions;

public class ClientAlreadyRegisteredException extends RuntimeException{

    public ClientAlreadyRegisteredException(String message){
        super(message);
    }
}
