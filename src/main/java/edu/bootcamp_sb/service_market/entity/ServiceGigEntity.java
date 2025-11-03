package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDateTime;
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

    private Double basePrice;

    private String priceType; //Hourly, per Job or for day

    private Double durationByHours;

    private String currency;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name= "provider_id")
    @JsonManagedReference
    private ProviderEntity serviceGigProvider;



}
