package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ClientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ClientService {

    ResponseEntity<List<ClientDto>> getAll();


    ResponseEntity<Map<String, String>> deleteById(Integer id);

    ResponseEntity<ClientDto> persist(ClientDto clientDto);

    ResponseEntity<ClientDto> updateByID(ClientDto clientDto);

}
