package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.OtpDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;
import edu.bootcamp_sb.service_market.dto.request.OtpCodeDto;
import edu.bootcamp_sb.service_market.dto.request.ProviderJobRequestDto;
import edu.bootcamp_sb.service_market.dto.request.UserDto;
import edu.bootcamp_sb.service_market.service.ClientService;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class KeyCloakUserHandleServiceImpl implements KeyCloakUserHandleService {

    private final ClientService clientService;
    private final ProviderService providerService;
    private final SecureRandom secureRandom;

    private OtpDto genereateOtp(UserDto userDto){
        OtpDto otpDto = new OtpDto();
        int otpValue = 10000 + secureRandom.nextInt(900000);
        Instant expireTime = Instant.now().plus(otpDto.getValidMinutes(), ChronoUnit.MINUTES);
        otpDto.setOtpCode(String.valueOf(otpValue));
        otpDto.setExpireTime(expireTime);
        otpDto.setTo(userDto.getEmail());


        return otpDto;
    }



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
