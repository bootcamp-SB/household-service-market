package edu.bootcamp_sb.service_market.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String address;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private String paymentMethod;

    private LocalDate createAt;

    private LocalDate updateAt;

    private String contact;




}
