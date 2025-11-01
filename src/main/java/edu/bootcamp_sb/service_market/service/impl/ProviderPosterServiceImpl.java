package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.ProviderPosterDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderPosterResponseDto;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.entity.ProviderGigPosterEntity;
import edu.bootcamp_sb.service_market.exception.providerException.ProviderHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.ProviderPosterRepository;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.service.ProviderPosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderPosterServiceImpl implements ProviderPosterService {

    private final ProviderPosterRepository providerPosterRepository;

    private final ProviderRepository providerRepository;


    @Override
    public ResponseEntity<ProviderPosterResponseDto> persistPoster(ProviderPosterDto posterDto) {
        ProviderGigPosterEntity posterEntity = new ProviderGigPosterEntity();
        posterEntity.setTopic(posterDto.getTopic());
        posterEntity.setPosterImg(posterDto.getPosterImg());
        posterEntity.setHourlyRate(posterDto.getHourlyRate());

        ProviderEntity providerEntity = providerRepository.findById(posterDto.getPosterProviderId()).orElseThrow(
                () -> new ProviderHasBeenNotFoundException("No provider has been found")
        );

        posterEntity.setPosterProvider(providerEntity);

        ProviderGigPosterEntity saved = providerPosterRepository.save(posterEntity);

        return ResponseEntity.ok().body(
                ProviderPosterResponseDto.builder()
                        .posterImg(saved.getPosterImg())
                        .topic(saved.getTopic())
                        .hourlyRate(saved.getHourlyRate())
                        .posterProvider(saved.getPosterProvider())
                        .build()
        );
    }
}
