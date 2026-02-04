package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String status;

    private String name; //client's name in case of they booking for some one else

    private String email; //client's email

    private String contactNo; //client's contact

    private String address; //client's address

    private String additionalInformation; // additional requests they want


    private LocalTime startingTime;

    private LocalDate startingDate;


    @OneToOne
    @JoinColumn(name="gig_id")
    private ServiceGigEntity gigEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    @JsonBackReference(value = "provider_booking")
    private ProviderEntity serviceProvider;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client-booking")
    private ClientEntity client;



}
