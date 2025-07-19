package edu.bootcamp_sb.service_market.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class JobRequestDto {

    private UUID id;

    private String name;

    private String type;

    private Long price;

    private Integer providerId;
}
