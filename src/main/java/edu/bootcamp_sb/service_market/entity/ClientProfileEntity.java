package edu.bootcamp_sb.service_market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Client_profile_pic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String profilePicUrl;
}
