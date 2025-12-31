package edu.bootcamp_sb.service_market.service;


import org.springframework.http.ResponseEntity;

public interface KeyCloakUserHandleService {

    ResponseEntity<String>createUser(
            String username,String lastname, String firstname, String email
    );

    

}
