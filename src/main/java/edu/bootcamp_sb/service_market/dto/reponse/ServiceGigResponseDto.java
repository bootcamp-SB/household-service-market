package edu.bootcamp_sb.service_market.dto.reponse;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceGigResponseDto {

    private UUID id;

    private String title;

    private String shortDescription;

    private String fullDescription;

    private Double basePrice;

    private String priceType; //Hourly, per Job or for day

    private String currency; // LKR, USD

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private CategoryResponseDto category;

    private ProviderDto provider;

}
