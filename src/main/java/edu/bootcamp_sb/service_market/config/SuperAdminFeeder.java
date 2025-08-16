package edu.bootcamp_sb.service_market.config;

import edu.bootcamp_sb.service_market.entity.AdminEntity;
import edu.bootcamp_sb.service_market.entity.AuthoritiesEntity;
import edu.bootcamp_sb.service_market.repository.AdminRepository;
import edu.bootcamp_sb.service_market.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class SuperAdminFeeder implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${SUPER_ADMIN_EMAIL}")
    private String email;

    @Value("${SUPER_ADMIN_PWD}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        if(adminRepository.findByEmail(email).isEmpty()){

            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setEmail(email);
            String encodedPassword = passwordEncoder.encode(password);
            adminEntity.setPassword(encodedPassword);
            adminEntity.setAuthorities(Collections.singleton(
                    new AuthoritiesEntity("ROLE_SUPER_ADMIN", adminEntity)));
            AdminEntity save = adminRepository.save(adminEntity);
            log.info(save.getEmail());
        }
    }
}
