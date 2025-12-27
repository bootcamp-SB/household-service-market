package edu.bootcamp_sb.service_market.service.impl;


import edu.bootcamp_sb.service_market.dto.ServiceGigDto;

import edu.bootcamp_sb.service_market.dto.reponse.ServiceGigResponseDto;
import edu.bootcamp_sb.service_market.entity.CategoryEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.entity.ServiceGigEntity;
import edu.bootcamp_sb.service_market.exception.category_exception.CategaryHasBeenNotFoundException;

import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderGigHasNotFound;
import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.CategoryRepository;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.repository.ServiceGigRepository;
import edu.bootcamp_sb.service_market.service.ServiceGigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static edu.bootcamp_sb.service_market.service.impl.CategoryServiceImpl.*;
import static edu.bootcamp_sb.service_market.service.impl.ProviderServiceImpl.convertProviderEntityToProviderDto;

@Service
@RequiredArgsConstructor
public class ServiceGigServiceImpl implements ServiceGigService {

    private final ServiceGigRepository gigRepository;

    private final ProviderRepository providerRepository;

    private final CategoryRepository categoryRepository;

    public static ServiceGigResponseDto convertGigEntityToGigResponseEntity(
            ServiceGigEntity preConvertedEntity
    ){
        ServiceGigResponseDto serviceGigResponseDto = new ServiceGigResponseDto();
        serviceGigResponseDto.setId(preConvertedEntity.getId());
        serviceGigResponseDto.setBasePrice(preConvertedEntity.getBasePrice());
        serviceGigResponseDto.setTitle(preConvertedEntity.getTitle());
        serviceGigResponseDto.setCreatedAt(preConvertedEntity.getCreatedAt());
        serviceGigResponseDto.setUpdatedAt(preConvertedEntity.getUpdatedAt());
        serviceGigResponseDto.setIsActive(preConvertedEntity.getIsActive());
        serviceGigResponseDto.setCurrency(preConvertedEntity.getCurrency());
        serviceGigResponseDto.setShortDescription(preConvertedEntity.getShortDescription());
        serviceGigResponseDto.setFullDescription(preConvertedEntity.getFullDescription());
        serviceGigResponseDto.setPriceType(preConvertedEntity.getPriceType());
        serviceGigResponseDto.setProvider(
                convertProviderEntityToProviderDto(preConvertedEntity.getServiceGigProvider())
        );
        serviceGigResponseDto.setCategory(
                convertCategoryEntityToCategoryResponseDto(preConvertedEntity.getCategory())
        );

        return  serviceGigResponseDto;
    }



    @Override
    public ResponseEntity<ServiceGigResponseDto> makeANewPoster(ServiceGigDto gig) {

        ServiceGigEntity serviceGigEntity = new ServiceGigEntity();
        serviceGigEntity.setBasePrice(gig.getBasePrice());
        serviceGigEntity.setCurrency(gig.getCurrency());
        serviceGigEntity.setTitle(gig.getTitle());
        serviceGigEntity.setCreatedAt(LocalDateTime.now());
        serviceGigEntity.setIsActive(Boolean.TRUE);
        serviceGigEntity.setShortDescription(gig.getShortDescription());
        serviceGigEntity.setFullDescription(gig.getFullDescription());
        serviceGigEntity.setPriceType(gig.getPriceType());
        serviceGigEntity.setUpdatedAt(LocalDateTime.now());


        ProviderEntity providerEntity = providerRepository.findById(gig.getProviderId()).orElseThrow(
                ()-> new ProviderHasBeenNotFoundException("Provider Has Been not Found")
        );

        serviceGigEntity.setServiceGigProvider(providerEntity);

        CategoryEntity categoryEntity = categoryRepository.findById(gig.getCategoryId()).orElseThrow(
                () -> new CategaryHasBeenNotFoundException("category not exsists"));

        serviceGigEntity.setCategory(categoryEntity);

        ServiceGigEntity save = gigRepository.save(serviceGigEntity);


        return ResponseEntity.ok().body(
                convertGigEntityToGigResponseEntity(save)
        );
    }

    @Override
    public ResponseEntity<List<ServiceGigResponseDto>> getAllGigs() {

        Iterable<ServiceGigEntity> serviceGigEntitiesList = gigRepository.findAll();

        ArrayList<ServiceGigResponseDto> serviceGigResponseDtoList = new ArrayList<>();

        serviceGigEntitiesList.forEach(serviceGigEntity ->
            serviceGigResponseDtoList.add(convertGigEntityToGigResponseEntity(serviceGigEntity))
        );

        return ResponseEntity.ok(serviceGigResponseDtoList);
    }

    @Override
    public ResponseEntity<List<ServiceGigResponseDto>> getAllActiveGigs() {
        Iterable<ServiceGigEntity> allByIsActive = gigRepository.findAllByIsActive(true);

        ArrayList<ServiceGigResponseDto> serviceGigResponseDtoList = new ArrayList<>();

        for(ServiceGigEntity serviceGig :allByIsActive){
            serviceGigResponseDtoList.add(convertGigEntityToGigResponseEntity(serviceGig));
        }

        return ResponseEntity.ok(serviceGigResponseDtoList);
    }

    @Override
    public ResponseEntity<Map<String, String>> countOfAllActiveJobs() {
        long activeJobsCount = gigRepository.countByIsActive(true);
        return ResponseEntity.ok(
                Map.of("Count of active gigs",String.valueOf(activeJobsCount))
        );
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteGigById(UUID id) {
        gigRepository.deleteById(id);

        return ResponseEntity.ok(Map.of("Message","successfully deleted"));
    }

    @Override
    public ResponseEntity<ServiceGigResponseDto> getById(UUID id) {

        ServiceGigEntity gigEntity = gigRepository.findById(id).orElseThrow(() ->
                new ProviderGigHasNotFound("not found"));


        return ResponseEntity.ok(convertGigEntityToGigResponseEntity(gigEntity));
    }
}
