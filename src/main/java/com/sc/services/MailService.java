package com.sc.services;

import javax.mail.internet.MimeMessage;

import com.sc.domain.Client;
import org.springframework.mail.SimpleMailMessage;

import com.sc.domain.OrderClass;

public interface MailService {
	
	void sendOrderConfirmationMail(OrderClass obj);
	
	void sendMail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlMail(OrderClass obj);
	
	void sendHtmlMail(MimeMessage msg);

	void sendNewPasswordEmail(Client client, String newPass);

}
