package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "service_providers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String contactNo;

    private Double hourlyRate;

    private String expertise;

    private Boolean isVerified;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<JobEntity> jobs;

}
