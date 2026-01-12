package edu.bootcamp_sb.service_market.dto;


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

    private String name; // in-case of client if booking for another one

    private String email;

    private String contactNo;

    private String address;

    private String service;

    private String additionalInformation;

    private String status; //pending, approved , rejected , canceled

    private String startingTime; //clients convenient starting time

    private LocalDate startingDate; //booking date

    private ServiceGigDto serviceGigDto;

    private ClientDto clientDto;

    private ProviderDto providerDto;



}
