package com.sc.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private UserDetailsService service;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JWTUtil jwtUtil, UserDetailsService service) {
        super(authenticationManager);
        this.jwtUtil=jwtUtil;
        this.service=service;
    }

    @Override
    protected void doFilterInternal (HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header!=null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken token = getAuthentication(header.substring(7));
            if (token!=null) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtil.validToken(token)) {
            String email = jwtUtil.getEmail(token);
            UserDetails user = service.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        }
        return null;
    }

}
