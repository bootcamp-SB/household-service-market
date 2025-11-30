package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ServiceGigDto;
import edu.bootcamp_sb.service_market.dto.reponse.ServiceGigResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ServiceGigService {

    ResponseEntity<ServiceGigResponseDto> makeANewPoster(ServiceGigDto gig);

    ResponseEntity<List<ServiceGigResponseDto>>getAllGigs();

    ResponseEntity<List<ServiceGigResponseDto>>getAllActiveGigs();

    ResponseEntity<Map<String,String>> countOfAllActiveJobs();

    ResponseEntity<Map<String,String>> deleteGigById(UUID id);


}
