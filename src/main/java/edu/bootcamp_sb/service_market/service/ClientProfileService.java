package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ClientProfileDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientProfileService {

    ResponseEntity<ClientProfileDto> create(ClientProfileDto profile);

    ResponseEntity<List<ClientProfileDto>> getAll();
}
