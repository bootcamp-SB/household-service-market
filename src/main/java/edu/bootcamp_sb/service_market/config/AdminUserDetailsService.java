package edu.bootcamp_sb.service_market.config;

import edu.bootcamp_sb.service_market.entity.AdminEntity;
import edu.bootcamp_sb.service_market.entity.ClientEntity;
import edu.bootcamp_sb.service_market.repository.AdminRepository;
import edu.bootcamp_sb.service_market.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AdminEntity admin = adminRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("not found"));


        List<SimpleGrantedAuthority> grantedAuthorities = admin.getAuthorities()
                .stream()
                .map(authority-> new SimpleGrantedAuthority(authority.getRole())).toList();

        return new User(admin.getEmail(), admin.getPassword() ,grantedAuthorities);
    }
}
