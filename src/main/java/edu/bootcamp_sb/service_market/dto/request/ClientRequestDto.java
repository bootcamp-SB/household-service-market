package edu.bootcamp_sb.service_market.dto.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    private UUID id;

    private String address;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private  String paymentMethod;


    private ClientProfileRequestDto profile;
}
