package edu.bootcamp_sb.service_market.service;


import org.keycloak.representations.idm.CredentialRepresentation;

public interface KeyCloakUserHandleService {

   String createUser(
            String username,String lastname, String firstname, String email

    );



}
