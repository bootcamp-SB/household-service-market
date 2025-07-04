package edu.bootcamp_sb.service_market.config;

import edu.bootcamp_sb.service_market.entity.ClientEntity;
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
public class ClientUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ClientEntity client = clientRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("not found"));

        List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                List.of(new SimpleGrantedAuthority(client.getRole()));

        return new User(client.getEmail(), client.getPassword() ,simpleGrantedAuthorities);
    }
}
