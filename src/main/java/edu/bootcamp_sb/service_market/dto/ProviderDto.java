package edu.bootcamp_sb.service_market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderDto {

    private Integer id;

    private String email;

    private String contactNo;

    private Double hourlyRate;

    private String expertise;

    private Boolean isVerified;

}
