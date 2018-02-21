package com.devopsbuddy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;



public class SmtpMailService extends AbstractEmailService {
	
	private static final Logger log=LoggerFactory.getLogger(SmtpMailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Override
	public void sendGenericEmail(SimpleMailMessage message)
	{
		
		log.debug("sending smtp");
		mailSender.send(message);
	}

	
}
