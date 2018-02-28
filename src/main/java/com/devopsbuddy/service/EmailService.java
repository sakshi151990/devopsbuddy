package com.devopsbuddy.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.devopsbuddy.web.FeedbackPojo;

@Service
public interface EmailService {

	public void sendfeeedbackEmail(FeedbackPojo feedbackpojo);

	public void sendGenericEmail(SimpleMailMessage simplemailmessage);

}
