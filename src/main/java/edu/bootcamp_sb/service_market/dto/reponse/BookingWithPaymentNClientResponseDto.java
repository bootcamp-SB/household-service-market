package edu.bootcamp_sb.service_market.dto.reponse;

import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.PaymentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingWithPaymentNClientResponseDto {

    private UUID id;

    private String status;

    private LocalTime startingTime;

    private LocalTime endingTime;

    private LocalDate startingDate;

    private LocalDate endingDate;

    private PaymentDto payment;

    private ClientDto client;

}
