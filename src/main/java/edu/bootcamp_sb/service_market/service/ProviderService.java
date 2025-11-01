package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderJobResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ProviderJobRequestDto;

import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;
import java.util.UUID;


public interface ProviderService {

    ResponseEntity<ProviderJobResponseDto> persistProviders(ProviderJobRequestDto provider);

    ResponseEntity<List<ProviderDto>> getAllProviders();

    ResponseEntity<Map<String,String>> deleteById(UUID id);

    ResponseEntity<Map<String, String>> deleteByListOfIds(Iterable<UUID>ids);

    ResponseEntity<ProviderDto> updateById(ProviderDto provider);

    ResponseEntity<ProviderJobResponseDto> getById(UUID id);

    ResponseEntity<List<ProviderDto>> getByListOfId(Iterable<UUID> id);

    ResponseEntity<List<ProviderDto>> findAllByExpertise(String expertise);

    ResponseEntity<List<ProviderJobResponseDto>> top5Providers();





}
