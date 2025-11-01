package edu.bootcamp_sb.service_market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "provider_poster")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderGigPosterEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "poster_id")
    private UUID id;

    private String topic;

    private String posterImg;

    private Double hourlyRate;

    @ManyToOne
    @JoinColumn(name = "providerId")
    private ProviderEntity posterProvider;



}
