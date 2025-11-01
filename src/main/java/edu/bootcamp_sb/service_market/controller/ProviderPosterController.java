package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ProviderPosterDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderPosterResponseDto;
import edu.bootcamp_sb.service_market.service.ProviderPosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posters")
public class ProviderPosterController {

    private final ProviderPosterService providerPosterService;

    @PostMapping
    public ResponseEntity<ProviderPosterResponseDto> persistPoster(@RequestBody ProviderPosterDto posterDto){
        return providerPosterService.persistPoster(posterDto);
    }

}
