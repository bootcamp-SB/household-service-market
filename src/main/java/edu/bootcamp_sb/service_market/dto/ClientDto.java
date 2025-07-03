package edu.bootcamp_sb.service_market.dto;

import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private UUID id;

    private String address;

    private String email;

    private  String paymentMethod;

    private String password;

    private String role;

    private ClientProfileEntity profile;

}
