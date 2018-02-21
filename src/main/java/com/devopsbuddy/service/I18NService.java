package com.devopsbuddy.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages="com.devopsbuddy.config")
public class I18NService {
	
	@Autowired
	private MessageSource messageSource;
	
	
	public String getMessage(String messageId)
	{
		
		Locale locale=LocaleContextHolder.getLocale();
		return getMessage(messageId,locale);
		
	}


	private String getMessage(String messageId, Locale locale) {
		// TODO Auto-generated method stub
		return messageSource.getMessage(messageId, null, locale);
	}
	

}
