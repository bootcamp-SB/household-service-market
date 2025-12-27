package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "gigs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceGigEntity {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String title;

    private String shortDescription;

    private String fullDescription;

    private Double basePrice;

    private String priceType; //Hourly, per Job or for day

    private String currency;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name= "provider_id")
    @JsonBackReference(value = "provider_gigs")
    private ProviderEntity serviceGigProvider;

    @OneToMany(mappedBy = "serviceGigEntity")
    @JsonManagedReference(value = "gig_review")
    private Set<ReviewsEntity> reviewsEntitySet;




}
