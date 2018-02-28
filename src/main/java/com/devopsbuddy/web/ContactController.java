package com.devopsbuddy.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devopsbuddy.service.EmailService;

@Controller
public class ContactController {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String getContact(ModelMap model) {
		System.out.println("inside contact controller");
		FeedbackPojo feedBackPojo = new FeedbackPojo();
		model.addAttribute("feedback", feedBackPojo);
		return "contact/contact";

	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String getfeedback(@ModelAttribute("feedback") FeedbackPojo feedbackpojo) {

		log.debug("getting feedback", feedbackpojo);
		emailService.sendfeeedbackEmail(feedbackpojo);
		return "contact/contact";
	}

}
