package com.devopsbuddy.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devopsbuddy.persistence.PasswordResetToken;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.service.EmailService;
import com.devopsbuddy.service.I18NService;
import com.devopsbuddy.service.PasswordResetTokenService;
import com.devopsbuddy.utils.UserUtil;

@Controller
public class ForgotMyPasswordContorller {

	@Autowired
	private PasswordResetTokenService passwordresettokenservice;

	public static final String CHANGE_PASSWORD_PATH = "/changeuserpassword";

	public static final String MAIL_SENT_KEY = "mailsent";

	public static final String EMAIL_MESSAGE_TEXT_PROPERTY_NAME = "forgotmypassword.email.text";

	@Autowired
	private I18NService i18NService;

	@Autowired
	private EmailService emailService;

	@Value("${webmaster.email}")
	private String webmasterEmail;

	@RequestMapping(value = "/forgotmypassword", method = RequestMethod.GET)
	public String forgotmypassword() {
		System.out.println("inside frogot");
		return "forgotmypassword/emailform";

	}

	@RequestMapping(value = "/forgotmypassword", method = RequestMethod.POST)
	public String forgotpasswordpos(HttpServletRequest request, @RequestParam("email") String email, ModelMap model) {
		PasswordResetToken passwordResetTolekn = passwordresettokenservice.createPasswordResetTokenForEmail(email);
		if (null == passwordResetTolekn) {
			System.out.println("Could not find password reset token for email id : " + email);
		} else {
			User user = passwordResetTolekn.getUser();
			String token = passwordResetTolekn.getToken();
			String resetpasswordURL = UserUtil.createPasswordResetURL(request, user.getId(), token);
			System.out.println(resetpasswordURL);
			String emailText = i18NService.getMessage(EMAIL_MESSAGE_TEXT_PROPERTY_NAME, request.getLocale());
			SimpleMailMessage smpmess = new SimpleMailMessage();
			smpmess.setTo(user.getEmail());
			smpmess.setSubject("DevopsBuddy: how to reset your password");
			smpmess.setText(emailText + "\r\n" + resetpasswordURL);
			smpmess.setFrom(webmasterEmail);

			emailService.sendGenericEmail(smpmess);

			System.out.println(smpmess.getTo() + smpmess.getText());

		}
		model.addAttribute(MAIL_SENT_KEY, "true");
		return "forgotmypassword/emailform";
	}
}
