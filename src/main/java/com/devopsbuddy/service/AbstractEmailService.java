package com.devopsbuddy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.devopsbuddy.web.FeedbackPojo;

public class AbstractEmailService implements EmailService {

	@Value("${default.to.address}")
	private String defaultToAddress;
	@Override
	public void sendfeeedbackEmail(FeedbackPojo feedbackpojo) {
		// TODO Auto-generated method stub
		
		sendGenericEmail(prepareSimpleMailMessageFromFeedbackPojo(feedbackpojo));
		
	}

	
	
	protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedbackpjo)
	{
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(defaultToAddress);
		simpleMailMessage.setFrom(feedbackpjo.getEmail());
		simpleMailMessage.setSubject("Devops feedback recived from "+feedbackpjo.getFirstName()+" ");
		simpleMailMessage.setText(feedbackpjo.getFeedback());
		
		return simpleMailMessage;}



	@Override
	public void sendGenericEmail(SimpleMailMessage simplemailmessage) {
		// TODO Auto-generated method stub
		
	}

}
