package edu.bootcamp_sb.service_market.dto.reponse;

import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.PaymentDto;
import edu.bootcamp_sb.service_market.entity.PaymentEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Data
@Builder
public class BookingResponseDto {

    private UUID id;

    private String status;

    private LocalTime startingTime;

    private LocalTime endingTime;

    private LocalDate date;

    private PaymentDto payment;

    private ClientDto client;
}
