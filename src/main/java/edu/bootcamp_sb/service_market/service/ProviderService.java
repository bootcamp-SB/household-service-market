package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ProviderDto;


import java.util.List;

public interface ProviderService {

    ProviderDto persistProviders(ProviderDto provider);

    List<ProviderDto> getAllProviders();

    String deleteById(Integer id);

    String deleteByListOfIds(Iterable<Integer>ids);

    ProviderDto updateById(ProviderDto provider);

    List<ProviderDto> getById(Iterable<Integer> listOfId);

    List<ProviderDto> findAllByExpertise(String expertise);

}
