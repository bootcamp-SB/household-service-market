package edu.bootcamp_sb.service_market.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name="client")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String address;

    private String email;

    private String paymentMethod;

    private String password;

    private String role;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_Id")
    @JsonManagedReference
    private ClientProfileEntity profile;
}
