package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ProviderDto;


import edu.bootcamp_sb.service_market.dto.reponse.ProviderCategoryResponseDto;
import edu.bootcamp_sb.service_market.dto.reponse.ProviderResponseDto;
import edu.bootcamp_sb.service_market.dto.request.ProviderSelectCategoriesDto;
import edu.bootcamp_sb.service_market.entity.ProviderEntity;
import edu.bootcamp_sb.service_market.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/providers")
public class ProviderController {

    private final ProviderService providerService;


    @PostMapping("/persist")
    public ResponseEntity<ProviderDto>persistProviders(@RequestBody ProviderDto provider)
    {
        return providerService.persistProviders(provider);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProviderDto>> getAllProviders(){
        return providerService.getAllProviders();
    }

    @GetMapping("/by-id")
    public ResponseEntity<ProviderResponseDto>getByID(@RequestParam UUID id){
        return providerService.getById(id);
    }

    @GetMapping("/by-list-id")
    public ResponseEntity<List<ProviderDto>> getByListOfID(@RequestBody Iterable<UUID> listOfId){
        return providerService.getByListOfId(listOfId);
    }

    @GetMapping("/by-expertises")
    public ResponseEntity<List<ProviderDto>> getByExpertise(@RequestParam String expertise){
        return providerService.findAllByExpertise(expertise);
    }

    @DeleteMapping("/by-id")
    public ResponseEntity<Map<String, String>> deleteById(@RequestParam UUID id){
        return providerService.deleteById(id);
    }

    @PatchMapping("/by-id")
    public ResponseEntity<ProviderDto> updateById(@RequestBody ProviderDto provider){
        return providerService.updateById(provider);
    }

    @DeleteMapping("/by-multiple-id")
    public ResponseEntity<Map<String, String>> deleteByRecordsByListOfIds(@RequestBody
                                                   List<UUID> ids)
    {
        return providerService.deleteByListOfIds(ids);

    }

    @GetMapping("/top5")
    public ResponseEntity<List<ProviderCategoryResponseDto>> getTop5(){
        return providerService.top5Providers();
    }

    @GetMapping("/with-categories")
    public ResponseEntity<List<ProviderCategoryResponseDto>>getAllProvidersWithCategories(){
        return providerService.getAllProvidersWithCategories();
    }

    @PutMapping("/select-categories")
    public ResponseEntity<Map<String,String>> selectCategories(
            @RequestBody ProviderSelectCategoriesDto selectingDto){
        return providerService.selectACategory(selectingDto);

    }
    @GetMapping("/count-all")
    public ResponseEntity<Map<String,String>>getCount(){
        return providerService.getProviderCount();
    }


}
