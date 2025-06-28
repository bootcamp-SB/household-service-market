package edu.bootcamp_sb.service_market.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    private Double amount;

    private String status;

    private Time timeStamp;

    @OneToOne
    @JoinColumn(name = "bookingId")
    @JsonManagedReference
    private BookingEntity booking;

}
