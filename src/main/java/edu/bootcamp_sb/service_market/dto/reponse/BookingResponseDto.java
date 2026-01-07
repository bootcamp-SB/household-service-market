package edu.bootcamp_sb.service_market.dto.reponse;


import edu.bootcamp_sb.service_market.dto.ProviderDto;
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
public class BookingResponseDto {

    private UUID id;

    private String status;

    private LocalTime startingTime;

    private LocalDate startingDate;

    private ClientResponseDto clientDto;

    private ProviderDto providerDto;

    private ServiceGigResponseDto serviceGigResponseDto;




}
