package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="reviews")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private Double rating;

    private String comment;

    private String providerResponse;

    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonBackReference(value = "client_review")
    private ClientEntity reviewsClient;

    @ManyToOne
    @JoinColumn(name="provider_id")
    @JsonBackReference(value = "provider_review")
    private ProviderEntity reviewsProvider;

    @ManyToOne
    @JoinColumn(name = "service_gig_id")
    @JsonBackReference(value="gig_review")
    private ServiceGigEntity serviceGigEntity;



}
