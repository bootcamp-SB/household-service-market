package edu.bootcamp_sb.service_market.dto.reponse;

import edu.bootcamp_sb.service_market.dto.ClientProfileDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClientResponseDto {

    private UUID id;

    private String address;

    private String email;

    private  String paymentMethod;

    private String firstName;

    private String lastName;

    private String username;


    private ClientProfileDto profile;
}
