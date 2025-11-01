package edu.bootcamp_sb.service_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "service_providers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String contactNo;

    private Double hourlyRate;

    private String expertise;

    private String address;

    private Boolean isVerified;

    private String experience;

    private Integer jobCount = 0;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.PERSIST)
    @JsonBackReference
    private List<JobEntity> jobs;

    @OneToMany(mappedBy = "serviceProvider",
            cascade = CascadeType.PERSIST ,
            orphanRemoval = true)
    @JsonManagedReference
    private Set<BookingEntity> booking;

    @OneToMany(mappedBy = "posterProvider")
    private List<ProviderGigPosterEntity> posterList;

}
