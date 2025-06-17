package edu.bootcamp_sb.service_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Integer id;

    private String address;

    private String email;

    private  String paymentMethod;

}
