package edu.bootcamp_sb.service_market.service.impl;


import edu.bootcamp_sb.service_market.dto.reponse.LoginResponseDto;
import edu.bootcamp_sb.service_market.dto.request.LoginRequestDto;
import edu.bootcamp_sb.service_market.entity.AdminEntity;
import edu.bootcamp_sb.service_market.entity.AuthoritiesEntity;
import edu.bootcamp_sb.service_market.repository.AdminRepository;
import edu.bootcamp_sb.service_market.service.AdminService;
import edu.bootcamp_sb.service_market.utill.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void registerSuperAdmin(String email, String password) {


        if(adminRepository.findByEmail(email).isEmpty()){

            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setEmail(email);
            String encodedPassword = passwordEncoder.encode(password);
            adminEntity.setPassword(encodedPassword);
            adminEntity.setAuthorities(Collections.singleton(
                    new AuthoritiesEntity("ROLE_ADMIN",adminEntity)));
            AdminEntity save = adminRepository.save(adminEntity);
            log.info(save.getEmail());
        }





    }

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequestDto.getUsername(), loginRequestDto.getPassword());
        Authentication authResponse;
        try {
            authResponse = authenticationManager.authenticate(authentication);
        } catch (Exception ex) {
            throw new BadCredentialsException("Bad credentials", ex);
        }

        if (authResponse == null || !authResponse.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }

        String generatedToken = "Bearer " + jwtTokenProvider.generateToken(authResponse);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", generatedToken)
                .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), generatedToken));
    }
}
