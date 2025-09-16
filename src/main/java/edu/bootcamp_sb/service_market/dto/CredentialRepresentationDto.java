package edu.bootcamp_sb.service_market.dto;

import lombok.Data;

@Data
public class CredentialRepresentationDto {

    private String type = "password";

    private String value;

    private Boolean temporary;

}
