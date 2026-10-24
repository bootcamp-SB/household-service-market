package edu.bootcamp_sb.service_market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
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

    private Set<Instant> busyDays;

}
