package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.dto.ClientProfileDto;
import edu.bootcamp_sb.service_market.service.ClientProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ClientProfileController {

    private final ClientProfileService profileService;

    @PostMapping
    public ResponseEntity<ClientProfileDto>create(@RequestBody ClientProfileDto profileDto){
        return profileService.create(profileDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientProfileDto>>getAll(){
        return profileService.getAll();
    }

}
