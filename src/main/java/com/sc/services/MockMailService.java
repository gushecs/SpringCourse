package com.sc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractMailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
	
	@Override
	public void sendMail(SimpleMailMessage msg) {
		LOG.info("Mail service simulation");
		LOG.info(msg.toString());
		LOG.info("Mail sent!");
		
	}

}
