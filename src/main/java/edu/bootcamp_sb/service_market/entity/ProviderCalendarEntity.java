package edu.bootcamp_sb.service_market.entity;

import edu.bootcamp_sb.service_market.sheard.ProviderAvailabilityStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "provider_calendar")
public class ProviderCalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private ProviderEntity provider;

    private Instant calendarDate;

    private ProviderAvailabilityStatus status;


}
