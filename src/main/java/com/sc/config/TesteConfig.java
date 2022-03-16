package com.sc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sc.services.DBService;
import com.sc.services.MailService;
import com.sc.services.MockMailService;

@Configuration
@Profile("teste")
public class TesteConfig {
	
	@Autowired
	DBService service;
	
	@Bean
	public boolean instantiateDatabase () throws ParseException {
		service.instantiateTesteDatabase();
		return true;
	}
	
	@Bean
	public MailService mailService() {
		return new MockMailService();
	}

}
