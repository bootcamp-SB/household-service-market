package edu.bootcamp_sb.service_market.dto;

import lombok.Data;


import java.util.UUID;

@Data
public class JobDto {

    private UUID id;

    private String name;

    private String type;

    private Long price;
}
