package edu.bootcamp_sb.service_market.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryRequestDto {

    private UUID id;

    private String name;


}
