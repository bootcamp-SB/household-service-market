package edu.bootcamp_sb.service_market.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.bootcamp_sb.service_market.entity.BookingEntity;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReviewsDto {

    private Double rating;

    private String comment;

    private String providerResponse;

    private LocalDateTime createdAt;

    private UUID bookingId;

    private UUID clientId;

    private UUID providerId;



}
