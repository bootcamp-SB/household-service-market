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


    private String name;

    private String email;

    private String contactNo;

    private String address; // if above field were null it will be replaced with client's info

    private String additionalInformation; // can be null

    private String status; //pending, approved , rejected , canceled

    private String startingTime; //clients convenient starting time

    private LocalDate startingDate; //booking date

    private UUID clientId;

    private UUID providerId;

    private UUID gigId;

}
