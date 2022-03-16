package com.sc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sc.services.DBService;
import com.sc.services.MailService;
import com.sc.services.SmtpMailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	DBService service;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase () throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		
		service.instantiateTesteDatabase();
		return true;
		
	}
	
	@Bean
	public MailService mailService() {
		return new SmtpMailService();
	}

}
