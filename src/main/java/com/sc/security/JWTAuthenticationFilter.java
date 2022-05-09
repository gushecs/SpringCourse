package com.sc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc.domain.DTO.CredentialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager manager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager manager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.manager = manager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        try {
            CredentialDTO credentialDTO = new ObjectMapper()
                    .readValue(request.getInputStream(), CredentialDTO.class);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    credentialDTO.getEmail(), credentialDTO.getPassword(), new ArrayList<>());

            Authentication authentication = manager.authenticate(token);
            return authentication;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication (HttpServletRequest request, HttpServletResponse response,
                                             FilterChain chain,Authentication authentication)
        throws IOException, ServletException {

        String email = ((UserSS) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(email);
        response.addHeader("Authorization","Bearer "+token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }

}
