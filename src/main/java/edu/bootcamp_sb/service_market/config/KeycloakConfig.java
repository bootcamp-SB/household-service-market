package edu.bootcamp_sb.service_market.config;



import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Value("${keycloak.base-url}")
    private String serverUrl;

    @Value("${keycloak.admin.username}")
    private String adminUsername;

    @Value("${keycloak.admin.password}")
    private String adminPassword;

    @Value("${keycloak.client-secret}")
    private String clientSecret;


    @Value("${keycloak.master-cli-id}")
    private String clientId;

    @Value("${keycloak.admin-realm}")
    private String adminRealm;



    @Bean
    public Keycloak keycloak() {

        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("master")
                .username(adminUsername)
                .password(adminPassword)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();

    }

    @Bean
    public String keycloakRealm() {
        return adminRealm;
    }

    public AccessTokenResponse getAccessToke(){
        return keycloak().tokenManager().getAccessToken();
    }


}
