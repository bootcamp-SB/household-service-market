package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.OtpDataDto;
import edu.bootcamp_sb.service_market.dto.OtpDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;


import edu.bootcamp_sb.service_market.dto.request.UserDto;
import edu.bootcamp_sb.service_market.exception.keycloak_user.FailedToCreateUserException;
import edu.bootcamp_sb.service_market.service.ClientService;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import edu.bootcamp_sb.service_market.service.ProviderService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static edu.bootcamp_sb.service_market.utill.UsernameSanitization.sanitizeUsername;


@Service
@RequiredArgsConstructor
@Slf4j
public class KeyCloakUserHandleServiceImpl implements KeyCloakUserHandleService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String marketRealm;

    HashMap<String,OtpDataDto> codes = new HashMap<>();

    private OtpDto generateOtp(UserDto userDto){
        OtpDto otpDto = new OtpDto();
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(999999);
        Instant expireTime = Instant.now().plus(otpDto.getValidMinutes(), ChronoUnit.MINUTES);
        String otpVCode = String.valueOf(otpValue);
        otpDto.setOtpCode(otpVCode);
        otpDto.setExpireTime(expireTime);
        otpDto.setTo(userDto.getEmail());

        OtpDataDto otpDataDtoHashMap = new OtpDataDto();
        otpDataDtoHashMap.setOtpCode(otpVCode);
        otpDataDtoHashMap.setExpireTime(expireTime);
        codes.put(userDto.getEmail(), otpDataDtoHashMap);

        return otpDto;
    }


    public void startCleanupTask() {
        scheduler.scheduleAtFixedRate(this::cleanupExpiredOtp, 5, 5, TimeUnit.MINUTES);
    }

    private void cleanupExpiredOtp() {
        Instant now = Instant.now();
        codes.entrySet().removeIf(entry -> now.isAfter(entry.getValue().getExpireTime()));
    }


    @Override
    public String createUser(
            String username,
            String lastname,
            String firstname ,
            String email,
            String password

    ) {

        UserRepresentation user = new UserRepresentation();
        user.setUsername(sanitizeUsername(username));
        user.setEmail(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEnabled(true);
        user.setEmailVerified(false);

        user.setCredentials(List.of(createPassword(password)));


        Response response = keycloak.realm(marketRealm).users().create(user);

        // 4. Check response status
        if (response.getStatus() == 201) {
            // Extract user ID from Location header
            String locationHeader = response.getHeaderString("Location");
            String userId =
                    locationHeader.substring(locationHeader.lastIndexOf('/') + 1);

            log.info("User created successfully with ID: {}", userId);
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


}
