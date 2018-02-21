package com.devopsbuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration

public class I18NConfig {

	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource resourceBundleMessageSource=new ReloadableResourceBundleMessageSource();
		resourceBundleMessageSource.addBasenames("classpath:i18n/messages_en");
		resourceBundleMessageSource.setCacheSeconds(1800);
		return resourceBundleMessageSource;
		
		
	}
	
}
