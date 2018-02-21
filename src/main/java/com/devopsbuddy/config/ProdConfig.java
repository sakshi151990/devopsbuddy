package com.devopsbuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.devopsbuddy.service.EmailService;
import com.devopsbuddy.service.SmtpMailService;

@Configuration
@Profile("prod")
@PropertySource("application-prod.properties")
public class ProdConfig {

	@Bean
	public EmailService emailService()
	{
		return new SmtpMailService();
	}
	
}
