package edu.bootcamp_sb.service_market.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="client")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    private UUID id;

    private String address;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private String paymentMethod;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_Id")
    @JsonManagedReference(value = "client-profile")
    private ClientProfileEntity profile;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference(value = "client-booking")
    private Set<BookingEntity> bookings;

    @OneToMany(mappedBy = "reviewsClient")
    @JsonManagedReference(value = "client_review")
    private List<ReviewsEntity> reviews;

}
