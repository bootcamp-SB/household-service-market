package edu.bootcamp_sb.service_market.dto.request;

import edu.bootcamp_sb.service_market.entity.ClientProfileEntity;
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

    private  String paymentMethod;

    private ClientProfileEntity profile;
}
