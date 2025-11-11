package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ServiceGigDto;
import edu.bootcamp_sb.service_market.dto.reponse.ServiceGigResponseDto;
import edu.bootcamp_sb.service_market.service.ServiceGigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gig")
public class ServiceGigController {
    private final ServiceGigService serviceGigService;

    @PostMapping
    public ResponseEntity<ServiceGigResponseDto> makeAGig(@RequestBody ServiceGigDto gig){
        return  serviceGigService.makeANewPoster(gig);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceGigResponseDto>> getAllGigs(){
        return serviceGigService.getAllGigs();
    }

}
