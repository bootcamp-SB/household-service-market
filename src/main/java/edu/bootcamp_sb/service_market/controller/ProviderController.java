package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ProviderDto;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/by-id")
    public List<ProviderDto> getByID(@RequestBody Iterable<Integer> listOfId){
        return providerService.getById(listOfId);
    }

    @GetMapping("/by-expertises")
    public List<ProviderDto>getByExpertise(@RequestParam String expertise){
        return providerService.findAllByExpertise(expertise);
    }

    @DeleteMapping("/by-id")
    public String deleteById(@RequestParam Integer id){
        return providerService.deleteById(id);
    }

    @PatchMapping("/by-id")
    public ProviderDto updateById(@RequestBody ProviderDto provider){
        return providerService.updateById(provider);
    }

    @DeleteMapping("/by-multiple-id")
    public String deleteByRecordsByListOfIds(@RequestBody List<Integer> ids){
        return providerService.deleteByListOfIds(ids);

    }



}
