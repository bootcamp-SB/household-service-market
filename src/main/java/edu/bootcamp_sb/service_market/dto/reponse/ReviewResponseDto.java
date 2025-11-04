package edu.bootcamp_sb.service_market.dto.reponse;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.bootcamp_sb.service_market.dto.BookingDto;
import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
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
public class ReviewResponseDto {
    private UUID id;

    private Double rating;

    private String comment;

    private String providerResponse;

    private LocalDateTime createdAt;

    private BookingDto booking;

    private ClientDto reviewsClient;

    private ProviderDto reviewsProvider;
}
