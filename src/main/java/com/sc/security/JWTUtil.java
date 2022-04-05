package com.sc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken (String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512,secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public boolean validToken(String token) {
        Claims claims = getClaims(token);
        String username = claims.getSubject();
        Date expirationDate = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());
        if (username!=null && expirationDate!=null && now.before(expirationDate)) {
            return true;
        }
        return false;
    }

    public String getEmail(String token) {
        Claims claims = getClaims(token);
        String username = claims.getSubject();
        return username;
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }

}
