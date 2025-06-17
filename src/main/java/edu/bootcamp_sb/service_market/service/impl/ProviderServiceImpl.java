package edu.bootcamp_sb.service_market.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.exception.providerException.ProviderExistAlreadyException;
import edu.bootcamp_sb.service_market.exception.providerException.ProviderHasBeenNotFoundException;
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

    private final ObjectMapper mapper;


    @Override
    public ResponseEntity<ProviderDto> persistProviders(ProviderDto provider) {
        Optional<ProviderEntity> contactNo =
                providerRepository.findByContactNo(provider.getContactNo());
        Optional<ProviderEntity> email =
                providerRepository.findByEmail(provider.getEmail());

        if(email.isPresent() && contactNo.isPresent()){
            throw new ProviderExistAlreadyException
                    ("Provider Already Exist with the Both email and contact number"
                            +" - "+provider.getContactNo()+" - "+provider.getEmail());
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

        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setEmail(provider.getEmail());
        providerEntity.setContactNo(provider.getContactNo());
        providerEntity.setHourlyRate(provider.getHourlyRate());
        providerEntity.setIsVerified(provider.getIsVerified());
        providerEntity.setExpertise(provider.getExpertise());

        return ResponseEntity.ok().body
                (
                        mapper.convertValue(
                        providerRepository.save(providerEntity)
                ,ProviderDto.class)
                );



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
        if(!providerRepository.existsById(provider.getId())){
            throw new ProviderHasBeenNotFoundException("Incorrect provider id");
        }


        return ResponseEntity.ok().body(mapper.convertValue(
                    providerRepository.save
                            (mapper.convertValue(provider, ProviderEntity.class)
                            ), ProviderDto.class));

    }

    @Override
    public ResponseEntity<Optional<ProviderEntity>> getById(Integer id) {
        if(!providerRepository.existsById(id)){
            throw new ProviderHasBeenNotFoundException("Incorrect provider id");
        }
        return ResponseEntity.ok().body(providerRepository.findById(id));

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
        return ResponseEntity.ok().body(listOfProviders);

    }


}
