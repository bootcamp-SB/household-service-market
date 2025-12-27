package edu.bootcamp_sb.service_market.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.bootcamp_sb.service_market.entity.BookingEntity;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsDto {

    private Double rating;

    private String comment;

    private String providerResponse;

    private LocalDateTime createdAt;

    private UUID serviceGigId;

    private UUID clientId;

    private UUID providerId;



}
