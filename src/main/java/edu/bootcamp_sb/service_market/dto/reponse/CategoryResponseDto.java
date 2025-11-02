package edu.bootcamp_sb.service_market.dto.reponse;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class CategoryResponseDto {

    private UUID id;

    private String name;


}
