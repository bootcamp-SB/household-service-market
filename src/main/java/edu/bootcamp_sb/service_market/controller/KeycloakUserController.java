package edu.bootcamp_sb.service_market.controller;


import edu.bootcamp_sb.service_market.dto.request.UserDto;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/admin/realms/market-realm/users")
@RequiredArgsConstructor
public class KeycloakUserController {

    private final KeyCloakUserHandleService keyCloakUserHandleService;

    @GetMapping("updateProfile")
    @PreAuthorize("hasAnyRole('user','admin','provider')")
    public void updateProfile(@RequestParam String userId){
        keyCloakUserHandleService.updateProfile(userId);
    }

    }
