package edu.bootcamp_sb.service_market.exception.keycloak_user;

import edu.bootcamp_sb.service_market.dto.reponse.ErrorResponse;

public class FailedToSendEmailException extends RuntimeException{
    public FailedToSendEmailException(String message){
         super(message);
    }
}
