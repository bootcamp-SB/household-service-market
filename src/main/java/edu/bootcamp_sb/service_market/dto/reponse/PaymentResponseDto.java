package edu.bootcamp_sb.service_market.dto.reponse;


import edu.bootcamp_sb.service_market.entity.BookingEntity;

import lombok.Builder;
import lombok.Data;


import java.sql.Time;
import java.util.UUID;

@Data
@Builder
public class PaymentResponseDto {


    private UUID id;

    private Double amount;

    private String status;

    private Time timeStamp;

    private BookingEntity booking;

}
