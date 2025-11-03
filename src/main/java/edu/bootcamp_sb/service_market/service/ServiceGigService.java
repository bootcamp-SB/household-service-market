package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ServiceGigDto;
import edu.bootcamp_sb.service_market.dto.reponse.ServiceGigResponseDto;
import org.springframework.http.ResponseEntity;

public interface ServiceGigService {

    ResponseEntity<ServiceGigResponseDto> makeANewPoster(ServiceGigDto gig);


}
