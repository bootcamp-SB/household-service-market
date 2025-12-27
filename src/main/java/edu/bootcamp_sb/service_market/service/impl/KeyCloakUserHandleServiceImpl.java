package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.OtpDataDto;
import edu.bootcamp_sb.service_market.dto.OtpDto;
import edu.bootcamp_sb.service_market.dto.request.ClientRequestDto;


import edu.bootcamp_sb.service_market.dto.request.UserDto;
import edu.bootcamp_sb.service_market.service.ClientService;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class KeyCloakUserHandleServiceImpl implements KeyCloakUserHandleService {

    private final ClientService clientService;
    private final ProviderService providerService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    HashMap<String,OtpDataDto> codes = new HashMap<>();

    private OtpDto generateOtp(UserDto userDto){
        OtpDto otpDto = new OtpDto();
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(999999);
        Instant expireTime = Instant.now().plus(otpDto.getValidMinutes(), ChronoUnit.MINUTES);
        String otpVCode = String.valueOf(otpValue);
        otpDto.setOtpCode(otpVCode);
        otpDto.setExpireTime(expireTime);
        otpDto.setTo(userDto.getEmail());

        OtpDataDto otpDataDtoHashMap = new OtpDataDto();
        otpDataDtoHashMap.setOtpCode(otpVCode);
        otpDataDtoHashMap.setExpireTime(expireTime);
        codes.put(userDto.getEmail(), otpDataDtoHashMap);

        return otpDto;
    }


    public void startCleanupTask() {
        scheduler.scheduleAtFixedRate(this::cleanupExpiredOtp, 5, 5, TimeUnit.MINUTES);
    }

    private void cleanupExpiredOtp() {
        Instant now = Instant.now();
        codes.entrySet().removeIf(entry -> now.isAfter(entry.getValue().getExpireTime()));
    }


    @Override
    public void clientUser(UserDto userDto) {

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setEmail(userDto.getEmail());
        clientRequestDto.setPaymentMethod(userDto.getPaymentMethod());
        clientRequestDto.setAddress(userDto.getAddress());

        clientService.persist(clientRequestDto);


    }


}
