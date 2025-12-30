package edu.bootcamp_sb.service_market.exception.keycloak_user;

public class FailedToCreateUserException extends RuntimeException{
    public FailedToCreateUserException(String message){super(message);}
}
