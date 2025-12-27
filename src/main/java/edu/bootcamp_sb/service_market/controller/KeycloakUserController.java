package edu.bootcamp_sb.service_market.controller;


import edu.bootcamp_sb.service_market.dto.request.UserDto;
import edu.bootcamp_sb.service_market.service.KeyCloakUserHandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/admin/realms/market-realm/users")
@RequiredArgsConstructor
public class KeycloakUserController {

    private final KeyCloakUserHandleService keyCloakUserHandleService;


    }
