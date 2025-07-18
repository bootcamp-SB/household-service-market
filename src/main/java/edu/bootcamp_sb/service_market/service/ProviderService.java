package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderJobResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ProviderJobRequestDto;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProviderService {

    ResponseEntity<ProviderJobResponseDto> persistProviders(ProviderJobRequestDto provider);

    ResponseEntity<List<ProviderDto>> getAllProviders();

    ResponseEntity<Map<String,String>> deleteById(Integer id);

    ResponseEntity<Map<String, String>> deleteByListOfIds(Iterable<Integer>ids);

    ResponseEntity<ProviderDto> updateById(ProviderDto provider);

    ResponseEntity<ProviderJobResponseDto> getById(Integer id);

    ResponseEntity<List<ProviderDto>> getByListOfId(Iterable<Integer> id);


    ResponseEntity<List<ProviderDto>> findAllByExpertise(String expertise);

}
