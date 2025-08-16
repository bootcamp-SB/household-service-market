package edu.bootcamp_sb.service_market.controller;


import edu.bootcamp_sb.service_market.dto.reponse.LoginResponseDto;
import edu.bootcamp_sb.service_market.dto.request.LoginRequestDto;
import edu.bootcamp_sb.service_market.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/login")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<LoginResponseDto>login(@RequestBody LoginRequestDto loginRequestDto){
        return adminService.login(loginRequestDto);
    }


}
