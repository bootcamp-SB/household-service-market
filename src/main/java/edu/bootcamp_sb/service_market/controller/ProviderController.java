package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping("/new-provider")
    public ProviderDto persistProviders(@RequestBody ProviderDto provider){
        return providerService.persistProviders(provider);
    }

    @GetMapping("/providers")
    public List<ProviderDto> getAllProviders(){
        return providerService.getAllProviders();
    }

}
