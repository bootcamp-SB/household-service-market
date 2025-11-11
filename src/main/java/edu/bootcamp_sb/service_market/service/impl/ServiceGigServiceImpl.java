package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.ServiceGigDto;
import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.reponse.ServiceGigResponseDto;
import edu.bootcamp_sb.service_market.entity.CategoryEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.entity.ServiceGigEntity;
import edu.bootcamp_sb.service_market.exception.category_exception.CategaryHasBeenNotFoundException;
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

import static edu.bootcamp_sb.service_market.service.impl.CategoryServiceImpl.*;
import static edu.bootcamp_sb.service_market.service.impl.ProviderServiceImpl.convertProviderEntityToProviderDto;

@Service
@RequiredArgsConstructor
public class ServiceGigServiceImpl implements ServiceGigService {

    private final ServiceGigRepository gigRepository;

    private final ProviderRepository providerRepository;

    private final CategoryRepository categoryRepository;



    @Override
    public ResponseEntity<ServiceGigResponseDto> makeANewPoster(ServiceGigDto gig) {

        ServiceGigEntity serviceGigEntity = new ServiceGigEntity();
        serviceGigEntity.setBasePrice(gig.getBasePrice());
        serviceGigEntity.setCurrency(gig.getCurrency());
        serviceGigEntity.setTitle(gig.getTitle());
        serviceGigEntity.setCreatedAt(LocalDateTime.now());
        serviceGigEntity.setIsActive(Boolean.TRUE);
        serviceGigEntity.setShortDescription(gig.getShortDescription());
        serviceGigEntity.setPriceType(gig.getPriceType());
        serviceGigEntity.setUpdatedAt(LocalDateTime.now());
        serviceGigEntity.setDurationByHours(gig.getDurationByHours());

        ProviderEntity providerEntity = providerRepository.findById(gig.getProviderId()).orElseThrow(
                ()-> new ProviderHasBeenNotFoundException("Provider Has Been not Found")
        );

        serviceGigEntity.setServiceGigProvider(providerEntity);

        CategoryEntity categoryEntity = categoryRepository.findById(gig.getCategoryId()).orElseThrow(
                () -> new CategaryHasBeenNotFoundException("category not exsists"));

        serviceGigEntity.setCategory(categoryEntity);

        ServiceGigEntity save = gigRepository.save(serviceGigEntity);

        ProviderDto providerDto = convertProviderEntityToProviderDto(providerEntity);
        CategoryResponseDto categoryDto =
                convertCategoryEntityToCategoryResponseDto(categoryEntity);


        return ResponseEntity.ok().body(
                ServiceGigResponseDto.builder()
                        .id(save.getId())
                        .basePrice(save.getBasePrice())
                        .title(save.getTitle())
                        .createdAt(save.getCreatedAt())
                        .updatedAt(save.getUpdatedAt())
                        .isActive(save.getIsActive())
                        .durationByHours(save.getDurationByHours())
                        .currency(save.getCurrency())
                        .shortDescription(save.getShortDescription())
                        .priceType(save.getPriceType())
                        .provider(providerDto)
                        .category(categoryDto)
                        .build()
        );
    }

    @Override
    public ResponseEntity<List<ServiceGigResponseDto>> getAllGigs() {

        Iterable<ServiceGigEntity> serviceGigEntitiesList = gigRepository.findAll();

        ArrayList<ServiceGigResponseDto> serviceGigResponseDtoList = new ArrayList<>();

        serviceGigEntitiesList.forEach(serviceGigEntity -> {
            ServiceGigResponseDto serviceGigResponseDto = new ServiceGigResponseDto();
            serviceGigResponseDto.setId(serviceGigEntity.getId());
            serviceGigResponseDto.setCategory(
                    convertCategoryEntityToCategoryResponseDto(serviceGigEntity.getCategory())
            );
            serviceGigResponseDto.setShortDescription(serviceGigEntity.getShortDescription());
            serviceGigResponseDto.setTitle(serviceGigEntity.getTitle());
            serviceGigResponseDto.setCurrency(serviceGigEntity.getCurrency());
            serviceGigResponseDto.setDurationByHours(serviceGigEntity.getDurationByHours());
            serviceGigResponseDto.setBasePrice(serviceGigEntity.getBasePrice());
            serviceGigResponseDto.setProvider(
                    convertProviderEntityToProviderDto(serviceGigEntity.getServiceGigProvider())
            );
            serviceGigResponseDto.setPriceType(serviceGigEntity.getPriceType());
            serviceGigResponseDtoList.add(serviceGigResponseDto);
        });

        return ResponseEntity.ok(serviceGigResponseDtoList);
    }
}
