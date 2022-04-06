package com.sc.services;

import com.sc.domain.Client;
import com.sc.repositories.ClientRepository;
import com.sc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private MailService mailService;

    private Random random = new Random();

    public void sendNewPassword (String email) {

        Client client = repository.findByEmail(email);

        if (client==null) {
            throw new ObjectNotFoundException("Email not found");
        }

        String newPass=newPassword();
        client.setPassword(pe.encode(newPass));

        repository.save(client);
        mailService.sendNewPasswordEmail(client,newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++) {
            vet[i]=randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt==0) { //generate digit
            return (char) (random.nextInt(10)+48);
        }else if (opt==1) { //generate capital letter
            return (char) (random.nextInt(26)+65);
        }else { //generate letter
            return (char) (random.nextInt(10)+97);
        }
    }

}
