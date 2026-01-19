package edu.bootcamp_sb.service_market.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderRegistrationDto {

    private String email;

    private String userName;

    private String firstName;

    private String lastName;

    private String password;

    private String contactNo;


}
