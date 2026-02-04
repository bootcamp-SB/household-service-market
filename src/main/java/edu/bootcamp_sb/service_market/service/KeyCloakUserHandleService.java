package edu.bootcamp_sb.service_market.service;


import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface KeyCloakUserHandleService {

   String createUser(
            String username,String lastname, String firstname, String email, String password,
            String role

    );

   CredentialRepresentation createPassword(String password);

   void assignRole(String role, String id);

   void sendVerificationMail(String userId);

    void updateProfile(String userId);

    ResponseEntity<Map<String, String>> logout(String userId);




}
