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
}
