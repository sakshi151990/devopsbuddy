package com.devopsbuddy.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.devopsbuddy.service.EmailService;
import com.devopsbuddy.service.MockEmailService;

@Configuration
@Profile("dev")
@PropertySource("classpath:application-dev.properties")
public class DevelopmentConfig {

	private String stripeKey = "hieufieffi";

	@Bean
	public EmailService emailService()

	{

		return new MockEmailService();
	}

	public ServletRegistrationBean h2consoleServletRegistrationBean() {
		ServletRegistrationBean serbean = new ServletRegistrationBean(new WebServlet());
		serbean.addUrlMappings("/h2-console");
		return serbean;

	}

	@Bean

	public String stripeKey() {
		return stripeKey;
	}

}
