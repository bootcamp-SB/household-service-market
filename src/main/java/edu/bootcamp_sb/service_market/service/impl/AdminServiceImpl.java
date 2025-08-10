package edu.bootcamp_sb.service_market.service.impl;

import edu.bootcamp_sb.service_market.dto.AdminDto;
import edu.bootcamp_sb.service_market.entity.AdminEntity;
import edu.bootcamp_sb.service_market.repository.AdminRepository;
import edu.bootcamp_sb.service_market.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private PasswordEncoder passwordEncoder;

    private AdminRepository adminRepository;

    @Override
    @Transactional
    public void registerSuperAdmin(String email, String password) {


        if(adminRepository.findByEmail(email).isEmpty()){

            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setEmail(email);
            String encodedPassword = passwordEncoder.encode(password);
            adminEntity.setPassword(encodedPassword);
            adminEntity.setRole("ROLE_SUPER_ADMIN");
            AdminEntity save = adminRepository.save(adminEntity);
            log.info(save.getEmail());
        }



    }
}
