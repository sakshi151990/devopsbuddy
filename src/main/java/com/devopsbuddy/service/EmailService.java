package com.devopsbuddy.service;

import org.springframework.mail.SimpleMailMessage;

import com.devopsbuddy.web.FeedbackPojo;

public interface EmailService {

	public void sendfeeedbackEmail(FeedbackPojo feedbackpojo);
	
	public void sendGenericEmail(SimpleMailMessage simplemailmessage);
	
}
