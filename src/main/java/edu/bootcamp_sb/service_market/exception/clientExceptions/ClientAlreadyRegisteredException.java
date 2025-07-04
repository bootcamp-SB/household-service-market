package edu.bootcamp_sb.service_market.exception.clientExceptions;

public class ClientAlreadyRegisteredException extends RuntimeException{

    public ClientAlreadyRegisteredException(String message){
        super(message);
    }
}
