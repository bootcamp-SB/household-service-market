package edu.bootcamp_sb.service_market.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "category")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

        @Id
        @UuidGenerator(style = UuidGenerator.Style.TIME)
        private UUID id;

        private String name;

        @ManyToMany(mappedBy = "categories")
        @JsonBackReference
        private Set<ProviderEntity> providers = new HashSet<>();

        @OneToMany(mappedBy = "category")
        @JsonBackReference
        private List<ServiceGigEntity> serviceGigList;


}
