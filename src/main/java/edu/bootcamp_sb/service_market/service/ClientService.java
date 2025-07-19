package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ClientService {

    ResponseEntity<List<ClientResponseDto>> getAll();


    ResponseEntity<Map<String, String>> deleteById(UUID id);

    ResponseEntity<ClientResponseDto> persist(ClientRequestDto clientDto);

    ResponseEntity<ClientDto> updateByID(ClientDto clientDto);

}
