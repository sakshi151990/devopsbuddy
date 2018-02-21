package com.devopsbuddy.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.devopsbuddy.service.EmailService;
import com.devopsbuddy.service.MockEmailService;

@Configuration
@Profile("dev")
@PropertySource("application-dev.properties")
public class DevelopmentConfig {

	@Bean
	public EmailService emailService()
	
	{
		
		return new MockEmailService();
	}
	
}
