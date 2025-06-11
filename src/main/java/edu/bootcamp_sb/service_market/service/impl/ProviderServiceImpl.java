package edu.bootcamp_sb.service_market.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.repository.ProviderRepository;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    private final ObjectMapper mapper;


    @Override
    public ProviderDto persistProviders(ProviderDto provider) {
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setEmail(provider.getEmail());
        providerEntity.setContactNo(provider.getContactNo());
        providerEntity.setHourlyRate(provider.getHourlyRate());
        providerEntity.setIsVerified(provider.getIsVerified());
        providerEntity.setExpertise(provider.getExpertise());

        ProviderEntity save = providerRepository.save(providerEntity);
        return mapper.convertValue(save,ProviderDto.class);



    }

    @Override
    public List<ProviderDto> getAllProviders() {

        Iterable<ProviderEntity> providers = providerRepository.findAll();

        ArrayList<ProviderDto> providersList = new ArrayList<>();
        providers.forEach(providerEntity ->
                providersList.add(mapper.convertValue
                        (providerEntity,ProviderDto.class)));
        return providersList;
    }





    public String deleteById(Integer id) {
        if(providerRepository.existsById(id)){
            providerRepository.deleteById(id);
            return "Provider's data has been Deleted";
        }
        return "Failed to deleted data";
        
    }

    @Override
    public String deleteByListOfIds(Iterable<Integer> ids) {
        providerRepository.deleteAllById(ids);
        return "Every records has been deleted";
    }

    @Override
    public ProviderDto updateById( ProviderDto provider) {
           return mapper.convertValue(
                    providerRepository.save
                            (mapper.convertValue(provider, ProviderEntity.class)
                            ), ProviderDto.class);

    }

    @Override
    public List<ProviderDto> getById(Iterable<Integer> listOfId) {

        Iterable<ProviderEntity> providersList = providerRepository.findAllById(listOfId);

        ArrayList<ProviderDto> selectedListOfProviders = new ArrayList<>();
        providersList.forEach(providerEntity ->
                selectedListOfProviders.add(mapper.convertValue
                        (providerEntity,ProviderDto.class)));
        return selectedListOfProviders;

    }

    @Override
    public List<ProviderDto> findAllByExpertise(String expertise) {
        Iterable<ProviderEntity> allByExpertise =
                providerRepository.findAllByExpertise(expertise);

        ArrayList<ProviderDto> listOfProviders = new ArrayList<>();

        allByExpertise.forEach(providerEntity->listOfProviders.add(mapper.convertValue
                (providerEntity,ProviderDto.class)));
        return listOfProviders;

    }


}
