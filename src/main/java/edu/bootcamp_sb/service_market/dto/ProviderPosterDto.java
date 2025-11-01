package edu.bootcamp_sb.service_market.dto;



import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class ProviderPosterDto {


    private String topic;

    private String posterImg;

    private Double hourlyRate;

    private UUID posterProviderId;
}
