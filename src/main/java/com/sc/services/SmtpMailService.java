package com.sc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpMailService extends AbstractMailService{

	private static final Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendMail(SimpleMailMessage msg) {
		LOG.info("Sending mail");
		mailSender.send(msg);
		LOG.info("Mail sent!");
		
	}
	
	@Override
	public void sendHtmlMail(MimeMessage msg) {
		LOG.info("Sending html mail");
		javaMailSender.send(msg);
		LOG.info("Mail sent!");
	}

}
