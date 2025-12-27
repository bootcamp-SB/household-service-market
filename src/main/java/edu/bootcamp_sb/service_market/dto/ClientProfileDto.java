package edu.bootcamp_sb.service_market.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientProfileDto {


    private UUID id;

    private String profilePicUrl;

    private ClientDto client;
}
