package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ProviderPosterDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderPosterResponseDto;
import org.springframework.http.ResponseEntity;

public interface ProviderPosterService {

  ResponseEntity<ProviderPosterResponseDto> persistPoster(ProviderPosterDto posterDto);

}
