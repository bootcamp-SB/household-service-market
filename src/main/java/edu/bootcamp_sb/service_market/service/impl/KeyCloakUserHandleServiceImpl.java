package edu.bootcamp_sb.service_market.service.impl;





import edu.bootcamp_sb.service_market.exception.keycloak_user.FailedToCreateUserException;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;




import static edu.bootcamp_sb.service_market.utill.UsernameSanitization.sanitizeUsername;


@Service
@RequiredArgsConstructor
@Slf4j
public class KeyCloakUserHandleServiceImpl implements KeyCloakUserHandleService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String marketRealm;




    @Override
    @Transactional
    public String createUser(
            String username,
            String lastname,
            String firstname ,
            String email,
            String password,
            String role

    ) {

        UserRepresentation user = new UserRepresentation();
        user.setUsername(sanitizeUsername(username));
        user.setEmail(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEnabled(true);
        user.setEmailVerified(false);

        //adding password
        user.setCredentials(List.of(createPassword(password)));


        Response response = keycloak.realm(marketRealm).users().create(user);



        // 4. Check response status
        if (response.getStatus() == 201) {
            // Extract user ID from Location header
            String locationHeader = response.getHeaderString("Location");
            String userId =
                    locationHeader.substring(locationHeader.lastIndexOf('/') + 1);

            log.info("User created successfully with ID: {}", userId);

            //assign role
            assignRole(role,userId);


            return userId;

        } else {
            String errorMessage = response.readEntity(String.class);
            log.error("Failed to create user. Status: {}, Error: {}", response.getStatus(), errorMessage);
            throw new FailedToCreateUserException(
                    "Failed to create user in Keycloak: " + errorMessage);
        }
    }

    @Override
    public CredentialRepresentation createPassword(String password) {
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setValue(password);
        credentials.setTemporary(false);
        credentials.setType(CredentialRepresentation.PASSWORD);
        return credentials;
    }

    @Override
    public void assignRole(String role, String id) {

        RoleRepresentation roleRepresentation =
                keycloak.realm(marketRealm).roles().get(role).toRepresentation();

        keycloak.realm(marketRealm)
                .users()
                .get(id)
                .roles()
                .realmLevel()
                .add(List.of(roleRepresentation));

    }

    @Override
    public void sendVerificationMail(String userId) {
        try{
            keycloak.realm(marketRealm).users().get(userId).sendVerifyEmail();
        }catch(Exception e){
            throw new RuntimeException("email error" + e);
        }
    }

    @Override
    public void updateProfile(String userId) {
        keycloak.realm(marketRealm).users().get(userId)
                .executeActionsEmail(List.of("UPDATE_PROFILE"));
    }


}
