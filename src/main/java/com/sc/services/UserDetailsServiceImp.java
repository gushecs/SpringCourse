package com.sc.services;

import com.sc.domain.Client;
import com.sc.repositories.ClientRepository;
import com.sc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = repository.findByEmail(email);
        if (client==null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(client.getId(),client.getEmail(),client.getPassword(),client.getProfiles());
    }
}
