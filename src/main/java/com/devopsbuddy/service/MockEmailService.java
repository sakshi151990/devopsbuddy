package com.devopsbuddy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger log=LoggerFactory.getLogger(MockEmailService.class);
	@Override
	public void sendGenericEmail(SimpleMailMessage message)
	{
		log.debug(message.toString());
	}
}
