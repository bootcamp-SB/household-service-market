package edu.bootcamp_sb.service_market.controller;

import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/keycloak")
@RequiredArgsConstructor
public class TestController {

    private final ClientRepository customerRepository;

    @GetMapping("/test/{name}")
    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String testOauth2(@PathVariable String name) {
       return name;

    }


}
