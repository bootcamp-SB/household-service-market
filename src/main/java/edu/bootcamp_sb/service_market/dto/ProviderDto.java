package edu.bootcamp_sb.service_market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String contactNo;

    private Double hourlyRate;

    private String expertise;

    private Boolean isVerified;

    private String address;

    private String experience;

    private Integer jobCount;

}
