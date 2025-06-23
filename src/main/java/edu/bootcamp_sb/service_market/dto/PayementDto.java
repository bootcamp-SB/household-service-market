package edu.bootcamp_sb.service_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayementDto {

    private UUID id;

    private Double amount;

    private String status;

    private Time timeStamp;
}
