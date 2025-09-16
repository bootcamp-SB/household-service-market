package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;
import edu.bootcamp_sb.service_market.dto.request.ProviderJobRequestDto;
import edu.bootcamp_sb.service_market.dto.request.UserDto;
import edu.bootcamp_sb.service_market.service.ClientService;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyCloakUserHandleServiceImpl implements KeyCloakUserHandleService {

    private final ClientService clientService;
    private final ProviderService providerService;

    @Override
    public void clientUser(UserDto userDto) {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setEmail(userDto.getEmail());
        clientRequestDto.setPaymentMethod(userDto.getPaymentMethod());
        clientRequestDto.setAddress(userDto.getAddress());

        clientService.persist(clientRequestDto);

    }

    @Override
    public void providerUser(UserDto userDto) {

        ProviderJobRequestDto providerDto = new ProviderJobRequestDto();
        providerDto.getProvider().setUserName(userDto.getUsername());
        providerDto.getProvider().setFirstName(userDto.getFirstName());
        providerDto.getProvider().setEmail(userDto.getEmail());
        providerDto.getProvider().setLastName(userDto.getLastName());
        providerDto.getProvider().setAddress(userDto.getAddress());

        providerService.persistProviders(providerDto);


    }
}
