package edu.bootcamp_sb.service_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private UUID id;

    private Double amount;

    private String status; // pending , done , rejected



}
