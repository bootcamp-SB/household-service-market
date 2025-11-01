package edu.bootcamp_sb.service_market.dto.reponse;

import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderPosterResponseDto {

    private String topic;

    private String posterImg;

    private Double hourlyRate;

    private ProviderEntity posterProvider;
}
