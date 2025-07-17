package edu.bootcamp_sb.service_market.dto.request;

import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
import lombok.Data;

import java.util.UUID;

@Data
public class ClientRequestDto {

    private UUID id;

    private String address;

    private String email;

    private  String paymentMethod;

    private String password;

    private String role;

    private ClientProfileEntity profile;
}
