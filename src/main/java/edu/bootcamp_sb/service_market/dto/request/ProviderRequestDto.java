package edu.bootcamp_sb.service_market.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderRequestDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private String contactNo;

    private String expertise;

    private Boolean isVerified;

    private String address;

    private String shortDescription;

    private String experience;

    private Integer jobCount;

}
