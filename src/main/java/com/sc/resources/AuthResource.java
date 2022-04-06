package com.sc.resources;

import com.sc.domain.DTO.MailDTO;
import com.sc.security.JWTUtil;
import com.sc.security.UserSS;
import com.sc.services.AuthService;
import com.sc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @PostMapping (value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer "+token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping (value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody MailDTO objDTO) {
        service.sendNewPassword(objDTO.getMail());
        return ResponseEntity.noContent().build();
    }

}
