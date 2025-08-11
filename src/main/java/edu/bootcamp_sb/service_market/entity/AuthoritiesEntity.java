package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="authorities")
public class AuthoritiesEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String role;

    @ManyToOne
    @JoinColumn(name= "admin_id")
    @JsonBackReference
    private AdminEntity admin;

    public AuthoritiesEntity(String name, AdminEntity adminEntity) {
        this.role = name;
        this.admin = adminEntity;
    }
}
