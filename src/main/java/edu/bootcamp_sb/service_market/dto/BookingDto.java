package edu.bootcamp_sb.service_market.dto;


import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private UUID id;

    private String status; //pending, approved , rejected , canceled

    private String startingTime; //clients convenient starting time

    private String endingTime; //coming from service gig time ending the service depend on that

    private LocalDate startingDate; //booking date

    private LocalDate endingDate; //if the service ran for days

    private PaymentDto payment;

    private UUID clientId;

    private UUID providerId;

}
