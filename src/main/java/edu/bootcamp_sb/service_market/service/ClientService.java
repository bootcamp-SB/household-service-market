package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ClientDto;
import edu.bootcamp_sb.service_market.dto.reponse.ClientResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ClientService {

    ResponseEntity<List<ClientResponseDto>> getAll();


    ResponseEntity<Map<String, String>> deleteById(Integer id);

    ResponseEntity<ClientResponseDto> persist(ClientDto clientDto);

    ResponseEntity<ClientDto> updateByID(ClientDto clientDto);

}
