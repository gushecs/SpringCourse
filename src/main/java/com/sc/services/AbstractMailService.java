package com.sc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sc.domain.OrderClass;

public abstract class AbstractMailService implements MailService {
	
	@Autowired
	private TemplateEngine engine;
	
	@Autowired
	private JavaMailSender mailSender;
	
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
	
	protected String htmlFromOrderTemplate(OrderClass obj) {
		Context context = new Context();
		context.setVariable("orderClass", obj);
		return engine.process("mail/OrderConfirmation", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlMail(OrderClass obj) {
		try {
		MimeMessage mm = prepareMimeMessageFromOrder(obj);
		sendHtmlMail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationMail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromOrder(OrderClass obj) throws MessagingException {
		MimeMessage mm = mailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm,true);
		mmh.setTo(obj.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order confirmed! Id: "+obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromOrderTemplate(obj),true);
		return mm;
	}

}
