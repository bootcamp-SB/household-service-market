package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ServiceGigDto;
import edu.bootcamp_sb.service_market.dto.reponse.ServiceGigResponseDto;
import edu.bootcamp_sb.service_market.service.ServiceGigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gig")
public class ServiceGigController {
    private final ServiceGigService serviceGigService;

    @PostMapping
    public ResponseEntity<ServiceGigResponseDto> makeAGig(@RequestBody ServiceGigDto gig){
        return  serviceGigService.makeANewPoster(gig);
    }

}
