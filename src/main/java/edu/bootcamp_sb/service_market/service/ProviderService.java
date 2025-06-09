package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;

import java.util.List;

public interface ProviderService {

    ProviderDto persistProviders(ProviderDto provider);

    List<ProviderDto> getAllProviders();
}
