package edu.bootcamp_sb.service_market.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Object realmAccessObj = source.getClaims().get("realm_access");
        if(realmAccessObj ==null){
            return Collections.emptyList();
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> realmAccess = (Map<String, Object>) realmAccessObj;

        Object rolesObj = realmAccess.get("roles");

        if(!(rolesObj instanceof List)){
            return Collections.emptyList();
        }

        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) rolesObj;



        return roles.stream()
                .filter(Objects::nonNull)
                .map(role->"ROLE_"+ role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
