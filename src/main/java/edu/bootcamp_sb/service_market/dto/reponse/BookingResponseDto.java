package edu.bootcamp_sb.service_market.dto.reponse;

import edu.bootcamp_sb.service_market.entity.PaymentEntity;



import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class BookingResponseDto {


    private Integer id;

    private String status;

    private Time timestamp;

    private Date date;

    private PaymentEntity payment;
}
