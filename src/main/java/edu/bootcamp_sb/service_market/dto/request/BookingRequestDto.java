package edu.bootcamp_sb.service_market.dto.request;

import edu.bootcamp_sb.service_market.dto.ServiceGigDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    private UUID id;

    private String status; //pending, approved , rejected , canceled

    private String startingTime; //clients convenient starting time

    private LocalDate startingDate; //booking date

    private UUID clientId;

    private UUID providerId;

    private UUID gigId;

}
