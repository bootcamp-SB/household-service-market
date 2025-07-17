package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "Client_profile_pic")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientProfileEntity {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String profilePicUrl;


    @OneToOne(mappedBy = "profile")
    @JsonBackReference
    private ClientEntity client;
}
