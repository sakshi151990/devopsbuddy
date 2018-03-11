package com.devopsbuddy.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = "com.devopsbuddy.config")
public class I18NService {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(I18NService.class);

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String messageId) {

		Locale locale = LocaleContextHolder.getLocale();
		log.info("returning the message for messag id");
		return getMessage(messageId, locale);

	}

	public String getMessage(String messageId, Locale locale) {
		// TODO Auto-generated method stub
		return messageSource.getMessage(messageId, null, locale);
	}

}
