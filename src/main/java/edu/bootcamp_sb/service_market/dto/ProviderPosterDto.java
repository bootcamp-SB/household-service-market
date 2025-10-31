package edu.bootcamp_sb.service_market.dto;

import edu.bootcamp_sb.service_market.entity.ProviderEntity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProviderPosterDto {

    private UUID id;

    private String topic;

    private String posterImg;

    private Double hourlyRate;

    private ProviderEntity posterProvider;
}
