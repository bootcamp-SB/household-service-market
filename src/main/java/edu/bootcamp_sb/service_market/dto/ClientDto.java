package edu.bootcamp_sb.service_market.dto;


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

    private String lastName;

    private String firstName;

    private String username;


    private  String paymentMethod;



}
