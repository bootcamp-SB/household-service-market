package edu.bootcamp_sb.service_market.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader("Authorization");

        try{
            if(jwtToken != null && jwtToken.startsWith("Bearer ")){

                jwtToken = jwtToken.substring(7);

                Environment environment = getEnvironment();
                String secret = environment.getProperty("JWT_SECRET_KEY",
                        "aP98WZQtnYvH4vFiw3kM5th7XoFVF5DkNvAfNhtrPTM=");

                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parser().verifyWith(secretKey)
                        .build().parseSignedClaims(jwtToken).getPayload();
                String username = String.valueOf(claims.get("username"));
                String authorities = String.valueOf(claims.get("authorities"));

                Authentication authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null
                                , AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }
        }catch(Exception ex){
            throw new BadCredentialsException("Invalid Token received!");
        }

        filterChain.doFilter(request,response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/v1/user/login")
                || request.getServletPath().equals("/api/v1/user/register");
        //This filter will execute every other scenarios except log in and register
    }

}
