package edu.bootcamp_sb.service_market.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.JobDto;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderJobResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ProviderJobRequestDto;
import edu.bootcamp_sb.service_market.entity.JobEntity;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.exception.clientExceptions.ClientHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.exception.providerException.ProviderExistAlreadyException;
import edu.bootcamp_sb.service_market.exception.providerException.ProviderHasBeenNotFoundException;
import edu.bootcamp_sb.service_market.repository.JobRepository;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    private final JobRepository jobRepository;

    private final ObjectMapper mapper;


    private static ProviderJobResponseDto convertProviderEntityToProviderJobResponseDto
            (ProviderEntity providerEntity) {

        ArrayList<JobEntity> jobEntities = new ArrayList<>(providerEntity.getJobs());

        ArrayList<JobDto> jobDtos = new ArrayList<>();

        for(JobEntity entity :jobEntities){
            JobDto jobDto = new JobDto();
            jobDto.setId(entity.getId());
            jobDto.setPrice(entity.getPrice());
            jobDto.setType(entity.getType());
            jobDto.setName(entity.getName());
            jobDtos.add(jobDto);
        }
        return ProviderJobResponseDto.builder()
                .id(providerEntity.getId())
                .contactNo(providerEntity.getContactNo())
                .hourlyRate(providerEntity.getHourlyRate())
                .email(providerEntity.getEmail())
                .expertise(providerEntity.getExpertise())
                .isVerified(providerEntity.getIsVerified())
                .job(jobDtos)
                .build();
    }


    @Override
    public ResponseEntity<ProviderJobResponseDto>
    persistProviders(ProviderJobRequestDto provider) {

        Optional<ProviderEntity> contactNo =
                providerRepository.findByContactNo(
                        provider.getProvider().getContactNo()
                );

        Optional<ProviderEntity> email =
                providerRepository.findByEmail(provider.getProvider().getEmail());

        if(email.isPresent() && contactNo.isPresent()){
            throw new ProviderExistAlreadyException
                    ("Provider Already Exist with the Both email and contact number"
                            +" - "+provider.getProvider().getContactNo()+" - "
                            +provider.getProvider().getEmail());
        }
        if(email.isPresent()){
            throw new ProviderExistAlreadyException
                    ("Provider Already Exist with the email"
                            +" - "+provider.getProvider().getEmail());
        }
        if(contactNo.isPresent()){
            throw new ProviderExistAlreadyException
                    ("Provider Already Exist with the contact number"
                            +" - "+provider.getProvider().getContactNo());
        }

        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setEmail(provider.getProvider().getEmail());
        providerEntity.setContactNo(provider.getProvider().getContactNo());
        providerEntity.setHourlyRate(provider.getProvider().getHourlyRate());
        providerEntity.setIsVerified(provider.getProvider().getIsVerified());
        providerEntity.setExpertise(provider.getProvider().getExpertise());

        ArrayList<JobDto> jobEntityList = new ArrayList<>(provider.getJobs());

        ArrayList<JobEntity> jobEntitiesSaveList = new ArrayList<>();

        for (JobDto entity : jobEntityList) {
            JobEntity jobEntity = new JobEntity();
            jobEntity.setPrice(entity.getPrice());
            jobEntity.setType(entity.getType());
            jobEntity.setName(entity.getName());
            jobEntity.setProvider(providerEntity);

            jobEntitiesSaveList.add(jobEntity);
        }

        providerEntity.setJobs(jobEntitiesSaveList);

        providerRepository.save(providerEntity);

        return ResponseEntity.ok().body
                (convertProviderEntityToProviderJobResponseDto(providerEntity));

    }




    @Override
    public ResponseEntity<List<ProviderDto>> getAllProviders() {

        Iterable<ProviderEntity> providers = providerRepository.findAll();

        ArrayList<ProviderDto> providersList = new ArrayList<>();

        providers.forEach(providerEntity ->
                providersList.add(mapper.convertValue
                        (providerEntity,ProviderDto.class)));

        return ResponseEntity.ok().body(providersList);
    }




    public ResponseEntity<Map<String, String>> deleteById(Integer id) {
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
    public ResponseEntity<Map<String, String>> deleteByListOfIds(Iterable<Integer> ids) {

        /* make exception for this */

        providerRepository.deleteAllById(ids);

        return ResponseEntity.ok().body(Map.of("List of deletion status message",
                "Every records has been deleted"));
    }

    @Override
    public ResponseEntity<ProviderDto> updateById(ProviderDto provider) {

        Optional<ProviderEntity> existingEntity =
                providerRepository.findById(provider.getId());


        if(existingEntity.isEmpty()){
            throw new ProviderHasBeenNotFoundException("Incorrect provider id");
        }
        ProviderEntity updatingEntity = existingEntity.get();

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
        if(provider.getHourlyRate() != null){
            updatingEntity.setHourlyRate(provider.getHourlyRate());
        }

        return ResponseEntity.ok().body(mapper.convertValue
                (providerRepository.save(updatingEntity),
                ProviderDto.class));

    }

    @Override
    public ResponseEntity<ProviderJobResponseDto> getById(Integer id) {
        if(!providerRepository.existsById(id)){
            throw new ProviderHasBeenNotFoundException("Incorrect provider id");
        }
        ProviderEntity providerEntity = providerRepository.findById(id).orElseThrow(
                ()-> new ProviderHasBeenNotFoundException("provider not found"));


        return ResponseEntity.ok().body(
                convertProviderEntityToProviderJobResponseDto(providerEntity)
        );


    }



    @Override
    public ResponseEntity<List<ProviderDto>> getByListOfId(Iterable<Integer> listOfId) {

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


}
