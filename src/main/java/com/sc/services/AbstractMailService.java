package com.sc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.sc.domain.OrderClass;

public abstract class AbstractMailService implements MailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationMail(OrderClass obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessage(obj);
		sendMail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessage(OrderClass obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Id: "+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
