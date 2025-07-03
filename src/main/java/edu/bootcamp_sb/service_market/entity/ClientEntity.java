package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name="client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "profile")
public class ClientEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String address;

    private String email;

    private String paymentMethod;

    private String password;

    private String role;

    @OneToOne(mappedBy = "client", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JsonManagedReference
    private ClientProfileEntity profile;
}
