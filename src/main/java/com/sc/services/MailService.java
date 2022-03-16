package com.sc.services;

import org.springframework.mail.SimpleMailMessage;

import com.sc.domain.OrderClass;

public interface MailService {
	
	void sendOrderConfirmationMail(OrderClass obj);
	
	void sendMail(SimpleMailMessage msg);

}
