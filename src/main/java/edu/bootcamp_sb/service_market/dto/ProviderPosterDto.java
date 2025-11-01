package edu.bootcamp_sb.service_market.dto;



import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class ProviderPosterDto {


    private String topic;

    private String posterImg;

    private Double hourlyRate;

    private Integer posterProviderId;
}
