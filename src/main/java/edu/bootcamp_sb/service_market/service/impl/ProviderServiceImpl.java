package edu.bootcamp_sb.service_market.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.CategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderCategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ProviderSelectCategoriesDto;
import edu.bootcamp_sb.service_market.entity.CategoryEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;

import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderExistAlreadyException;
import edu.bootcamp_sb.service_market.exception.provider_exception.ProviderHasBeenNotFoundException;

import edu.bootcamp_sb.service_market.repository.CategoryRepository;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;

import java.util.*;

import static edu.bootcamp_sb.service_market.service.impl.CategoryServiceImpl.convertCategoryEntityToCategoryResponseDto;

@Service
@RequiredArgsConstructor
@EnableMethodSecurity
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    private final CategoryRepository categoryRepository;

    private final ObjectMapper mapper;

    public static ProviderDto convertProviderEntityToProviderDto(ProviderEntity preConvertDto){
        return ProviderDto.builder()
                .id(preConvertDto.getId())
                .firstName(preConvertDto.getFirstName())
                .lastName(preConvertDto.getLastName())
                .userName(preConvertDto.getUserName())
                .email(preConvertDto.getEmail())
                .isVerified(preConvertDto.getIsVerified())
                .expertise(preConvertDto.getExpertise())
                .contactNo(preConvertDto.getContactNo())
                .address(preConvertDto.getAddress())
                .experience(preConvertDto.getExperience())
                .jobCount(preConvertDto.getJobCount())
                .build();
    }

    private static ProviderEntity getProviderEntityFromProviderDto(ProviderDto provider) {
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setEmail(provider.getEmail());
        providerEntity.setFirstName(provider.getFirstName());
        providerEntity.setLastName(provider.getLastName());
        providerEntity.setUserName(provider.getUserName());
        providerEntity.setContactNo(provider.getContactNo());
        providerEntity.setIsVerified(provider.getIsVerified());
        providerEntity.setExpertise(provider.getExpertise());
        providerEntity.setAddress(provider.getAddress());
        if(provider.getExperience() == null){
            providerEntity.setExperience("0 years");
        } else{
            providerEntity.setExperience(provider.getExperience());
        }
        if(provider.getJobCount() != null ){
            providerEntity.setJobCount(provider.getJobCount());
        }
        return providerEntity;
    }




    @Override
    public ResponseEntity<ProviderDto> persistProviders(ProviderDto provider) {

        Optional<ProviderEntity> contactNo =
                providerRepository.findByContactNo(
                        provider.getContactNo()
                );

        Optional<ProviderEntity> email =
                providerRepository.findByEmail(provider.getEmail());

        if(email.isPresent() && contactNo.isPresent()){
            throw new ProviderExistAlreadyException
                    ("Provider Already Exist with the Both email and contact number"
                            +" - "+provider.getContactNo()+" - "
                            +provider.getEmail());
        }
        if(email.isPresent()){
            throw new ProviderExistAlreadyException
                    ("Provider Already Exist with the email"
                            +" - "+provider.getEmail());
        }
        if(contactNo.isPresent()){
            throw new ProviderExistAlreadyException
                    ("Provider Already Exist with the contact number"
                            +" - "+provider.getContactNo());
        }

        ProviderEntity providerEntity = getProviderEntityFromProviderDto(provider);

        ProviderEntity save = providerRepository.save(providerEntity);

        return ResponseEntity.ok().body(convertProviderEntityToProviderDto(save));

    }



    @Override
    public ResponseEntity<List<ProviderDto>> getAllProviders() {

        Iterable<ProviderEntity> providers = providerRepository.findAll();

        ArrayList<ProviderDto> providersList = new ArrayList<>();

        providers.forEach(providerEntity ->
            providersList.add(convertProviderEntityToProviderDto(providerEntity))
        );

        return ResponseEntity.ok().body(providersList);
    }



    @Override
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<Map<String, String>> deleteById(UUID id) {
        if(providerRepository.existsById(id)){
            providerRepository.deleteById(id);
            return ResponseEntity.ok().body(
                    Map.of("Deletion status message",
                            "Provider's data has been Deleted"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("Not found message","Provider's data has been Deleted"));
        
    }



    @Override
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Map<String, String>> deleteByListOfIds(Iterable<UUID> ids) {

        /* make exception for this */

        providerRepository.deleteAllById(ids);

        return ResponseEntity.ok().body(Map.of("List of deletion status message",
                "Every records has been deleted"));
    }

    @Override
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<ProviderDto> updateById(ProviderDto provider) {

        Optional<ProviderEntity> existingEntity =
                providerRepository.findById(provider.getId());


        if(existingEntity.isEmpty()){
            throw new ProviderHasBeenNotFoundException("Incorrect provider id");
        }
        ProviderEntity updatingEntity = existingEntity.get();

        if(provider.getFirstName()!=null){
            updatingEntity.setFirstName(provider.getFirstName());
        }
        if(provider.getLastName()!=null){
            updatingEntity.setLastName(provider.getLastName());
        }
        if(provider.getUserName()!=null){
            updatingEntity.setUserName(provider.getUserName());
        }
        if(provider.getEmail() != null){
            updatingEntity.setEmail(provider.getEmail());
        }
        if(provider.getContactNo() != null){
            updatingEntity.setContactNo(provider.getContactNo());
        }
        if(provider.getIsVerified() != null){
            updatingEntity.setIsVerified(provider.getIsVerified());
        }
        if(provider.getExpertise() != null){
            updatingEntity.setExpertise(provider.getExpertise());
        }


        return ResponseEntity.ok().body(mapper.convertValue
                (providerRepository.save(updatingEntity),
                ProviderDto.class));

    }

    @Override
    @PreAuthorize("hasAnyRole('admin','provider')")
    public ResponseEntity<ProviderDto> getById(UUID id) {
        if(!providerRepository.existsById(id)){
            throw new ProviderHasBeenNotFoundException("Incorrect provider id");
        }
        ProviderEntity providerEntity = providerRepository.findById(id).orElseThrow(
                ()-> new ProviderHasBeenNotFoundException("provider not found"));


        return ResponseEntity.ok().body(
                convertProviderEntityToProviderDto(providerEntity)
        );


    }


    @Override
    public ResponseEntity<List<ProviderDto>> getByListOfId(Iterable<UUID> listOfId) {

        Iterable<ProviderEntity> providersList = providerRepository.findAllById(listOfId);

        ArrayList<ProviderDto> selectedListOfProviders = new ArrayList<>();
        providersList.forEach(providerEntity ->
                selectedListOfProviders.add(mapper.convertValue
                        (providerEntity,ProviderDto.class)));
        return ResponseEntity.ok().body(selectedListOfProviders);

    }

    @Override
    public ResponseEntity<List<ProviderDto>> findAllByExpertise(String expertise) {
        Iterable<ProviderEntity> allByExpertise =
                providerRepository.findAllByExpertise(expertise);

        ArrayList<ProviderDto> listOfProviders = new ArrayList<>();

        allByExpertise.forEach(providerEntity->listOfProviders.add(mapper.convertValue
                (providerEntity,ProviderDto.class)));
        if(listOfProviders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listOfProviders);
        }
        return ResponseEntity.ok().body(listOfProviders);


    }

    @Override
    public ResponseEntity<List<ProviderCategoryResponseDto>> top5Providers() {
        List<ProviderEntity> top5ByOrderByJobCountDesc =
                providerRepository.findTop5ByOrderByJobCountDesc(
                        PageRequest.of(0, 5, Sort.by(
                                Sort.Direction.DESC, "jobCount")));

        ArrayList<ProviderCategoryResponseDto> responseDtoArrayList = new ArrayList<>();

        top5ByOrderByJobCountDesc.forEach(top5Entity->{
            ProviderCategoryResponseDto providerCategoryResponseDto =
                    new ProviderCategoryResponseDto();

            ProviderDto providerResponseDto
                    = convertProviderEntityToProviderDto(top5Entity);

            providerCategoryResponseDto.setProviderDto(providerResponseDto);

            HashSet<CategoryResponseDto> categoryDtoHashSet = new HashSet<>();

            for(CategoryEntity categoryEntity : top5Entity.getCategories()){
                categoryDtoHashSet.add(convertCategoryEntityToCategoryResponseDto(categoryEntity));

            }
            providerCategoryResponseDto.setCategoriesSet(categoryDtoHashSet);

            responseDtoArrayList.add(providerCategoryResponseDto);
        });

        return ResponseEntity.ok(responseDtoArrayList);
    }

    @Override
    public ResponseEntity<List<ProviderCategoryResponseDto>> getAllProvidersWithCategories(){

        List<ProviderEntity> providerEntityList = providerRepository.findAll();

        ArrayList<ProviderCategoryResponseDto> providerCategoryResponseDtosList = new ArrayList<>();

        HashSet<CategoryResponseDto> categoryDtos = new HashSet<>();


        for(ProviderEntity providerEntity : providerEntityList){
           ProviderCategoryResponseDto providerCategoryResponseDto = new ProviderCategoryResponseDto();

           providerCategoryResponseDto.setProviderDto(
                   convertProviderEntityToProviderDto(providerEntity)
           );

           providerEntity.getCategories().forEach(categoryEntity ->
                   categoryDtos.add(convertCategoryEntityToCategoryResponseDto(categoryEntity))
           );

           providerCategoryResponseDto.setCategoriesSet(categoryDtos);
           providerCategoryResponseDtosList.add(providerCategoryResponseDto);

       }

        return ResponseEntity.ok(providerCategoryResponseDtosList);
    }

    @Override
    public ResponseEntity<Map<String, String>> selectACategory(ProviderSelectCategoriesDto selectCategoriesDto) {
        ProviderEntity providerEntity = providerRepository.findById(
                selectCategoriesDto.getProviderId()).orElseThrow(
                        () -> new ProviderHasBeenNotFoundException("Not Found")
        );


        Iterable<CategoryEntity> allTheCategories = categoryRepository.findAllById(
                selectCategoriesDto.getCategoriesId());

        HashSet<CategoryEntity> categoryEntities = new HashSet<>();

        HashSet<ProviderEntity> providerEntities = new HashSet<>();
        providerEntities.add(providerEntity);

        if(allTheCategories == null){
            return ResponseEntity.badRequest().body(Map.of("Failed", "Categories not exists"));
        }

        allTheCategories.forEach(entity->{
            entity.setProviders(providerEntities);
            categoryEntities.add(entity);
        });

        if(providerEntity.getCategories().isEmpty()){
            providerEntity.setCategories(categoryEntities);
        }
        providerRepository.save(providerEntity);

        return ResponseEntity.ok(Map.of("Success", "Categories Added to provider"));
    }

    @Override
    public ResponseEntity<Map<String, String>> getProviderCount() {
        long count = providerRepository.count();
        return ResponseEntity.ok(
                Map.of("Provider count", String.valueOf(count))
        );
    }


}


