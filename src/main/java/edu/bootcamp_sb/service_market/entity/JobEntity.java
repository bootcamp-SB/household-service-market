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
@Table(name = "service")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

        @Id
        @UuidGenerator(style = UuidGenerator.Style.TIME)
        private UUID id;

        private String name;

        private String type;

        private Long price;

        @ManyToOne
        @JoinColumn(name = "provider_id")
        @JsonManagedReference
        private ProviderEntity provider;
}
