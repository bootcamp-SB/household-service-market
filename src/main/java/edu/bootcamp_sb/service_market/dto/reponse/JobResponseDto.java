package edu.bootcamp_sb.service_market.dto.reponse;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class JobResponseDto {

    private UUID id;

    private String name;

    private String type;

    private Long price;

    private ProviderDto provider;
}
