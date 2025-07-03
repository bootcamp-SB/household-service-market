package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "Client_profile_pic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "client")
public class ClientProfileEntity {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String profilePicUrl;


    @OneToOne
    @JoinColumn(name = "client_Id")
    @JsonBackReference
    private ClientEntity client;
}
