package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ServiceGigDto;
import edu.bootcamp_sb.service_market.dto.reponse.ServiceGigResponseDto;
import edu.bootcamp_sb.service_market.service.ServiceGigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/count-active-ones")
    public ResponseEntity<Map<String,String>> activeGigCount(){
        return serviceGigService.countOfAllActiveJobs();
    }

    @GetMapping("/active-posters")
    public ResponseEntity<List<ServiceGigResponseDto>>getAllActiveGigs(){
        return serviceGigService.getAllActiveGigs();
    }

    @DeleteMapping("/by-id")
    public ResponseEntity<Map<String,String>>deleteById(@RequestParam UUID id){
        return serviceGigService.deleteGigById(id);
    }

    @GetMapping("/by-id")
    public ResponseEntity<ServiceGigResponseDto>getById(@RequestParam UUID id){
        return serviceGigService.getById(id);
    }

    @GetMapping("/count-by/provider")
    public ResponseEntity<Map<String,Integer>>getCountByProviderId(@RequestParam String id){
        return  serviceGigService.getCountByProviderId(id);
    }

}
