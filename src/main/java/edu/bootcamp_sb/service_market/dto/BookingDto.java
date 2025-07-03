package edu.bootcamp_sb.service_market.dto;


import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private UUID id;

    private String status;

    private String startingTime;

    private String endingTime;

    private String date;

    private PaymentEntity payment;

}
