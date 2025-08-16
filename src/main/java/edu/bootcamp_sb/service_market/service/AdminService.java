package edu.bootcamp_sb.service_market.service;

import edu.bootcamp_sb.service_market.dto.reponse.LoginResponseDto;
import edu.bootcamp_sb.service_market.dto.request.LoginRequestDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    void registerSuperAdmin(String email , String Password);

    ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto);

}
