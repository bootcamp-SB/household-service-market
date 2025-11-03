package edu.bootcamp_sb.service_market.dto;


import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
public class ServiceGigDto {

    private String title;

    private String shortDescription;

    private Double basePrice;

    private String priceType; //Hourly, per Job or for day

    private Double durationByHours;

    private String currency; // LKR, USD

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID categoryId;

    private UUID providerId;

}
