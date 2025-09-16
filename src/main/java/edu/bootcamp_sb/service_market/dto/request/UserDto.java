package edu.bootcamp_sb.service_market.dto.request;

import edu.bootcamp_sb.service_market.dto.CredentialRepresentationDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String paymentMethod;

    private List<CredentialRepresentationDto> credentials;

    private List<String> realmRoles;
}
