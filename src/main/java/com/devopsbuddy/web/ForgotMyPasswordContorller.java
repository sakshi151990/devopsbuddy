package com.devopsbuddy.web;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.devopsbuddy.persistence.PasswordResetToken;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.service.EmailService;
import com.devopsbuddy.service.I18NService;
import com.devopsbuddy.service.PasswordResetTokenService;
import com.devopsbuddy.service.UserService;
import com.devopsbuddy.utils.UserUtil;

@Controller
public class ForgotMyPasswordContorller {

	private static final Logger LOG = LoggerFactory.getLogger(ForgotMyPasswordContorller.class);
	@Autowired
	private PasswordResetTokenService passwordresettokenservice;

	public static final String CHANGE_PASSWORD_PATH = "/changeuserpassword";

	public static final String MAIL_SENT_KEY = "mailsent";

	public static final String EMAIL_MESSAGE_TEXT_PROPERTY_NAME = "forgotmypassword.email.text";

	public static final String CHANGE_PASSWORD_VIEW_NAME = "forgotmypassword/changePassword";
	private static final String PASSWORD_RESET_ATTRIBUTE_NAME = "passwordReset";

	private static final String MESSAGE_ATTRIBUTE_NAME = "message";

	@Autowired
	private I18NService i18NService;

	@Autowired
	private EmailService emailService;

	@Value("${webmaster.email}")
	private String webmasterEmail;

	@Autowired
	private UserService userService;

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

	@RequestMapping(value = CHANGE_PASSWORD_PATH)
	public String changeUserPassword(@RequestParam("id") long id, @RequestParam("token") String token, Locale locale,
			ModelMap model) {
		if (StringUtils.isEmpty(token) || id == 0) {

			model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, "false");
			model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "Invalid user id or token value");
			return CHANGE_PASSWORD_VIEW_NAME;
		}

		PasswordResetToken passwordResetToken = passwordresettokenservice.findByToken(token);

		if (null == passwordResetToken) {
			LOG.warn("A token couldn't be found with value {}", token);
			model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, "false");
			model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "Token not found");
			return CHANGE_PASSWORD_VIEW_NAME;
		}

		User user = passwordResetToken.getUser();
		if (user.getId() != id) {
			LOG.error("The user id {} passed as parameter does not match the user id {} associated with the token {}",
					id, user.getId(), token);
			model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, "false");
			model.addAttribute(MESSAGE_ATTRIBUTE_NAME, i18NService.getMessage("resetPassword.token.invalid", locale));
			return CHANGE_PASSWORD_VIEW_NAME;
		}

		if (LocalDateTime.now(Clock.systemUTC()).isAfter(passwordResetToken.getExpiryDate())) {
			LOG.error("The token {} has expired", token);
			model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, "false");
			model.addAttribute(MESSAGE_ATTRIBUTE_NAME, i18NService.getMessage("resetPassword.token.expired", locale));
			return CHANGE_PASSWORD_VIEW_NAME;
		}

		model.addAttribute("principalId", user.getId());

		// OK to proceed. We auto-authenticate the user so that in the POST request we
		// can check if the user
		// is authenticated
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		return CHANGE_PASSWORD_VIEW_NAME;
	}

	@RequestMapping(value = CHANGE_PASSWORD_PATH, method = RequestMethod.POST)
	public String changeUserPasswordPost(@RequestParam("principal_id") long userId,
			@RequestParam("password") String password, ModelMap model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null == authentication) {
			LOG.error("An unauthenticated user tried to invoke the reset password POST method");
			model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, "false");
			model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "You are not authorized to perform this request.");
			return CHANGE_PASSWORD_VIEW_NAME;
		}

		User user = (User) authentication.getPrincipal();
		if (user.getId() != userId) {
			LOG.error("Security breach! User {} is trying to make a password reset request on behalf of {}",
					user.getId(), userId);
			model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, "false");
			model.addAttribute(MESSAGE_ATTRIBUTE_NAME, "You are not authorized to perform this request.");
			return CHANGE_PASSWORD_VIEW_NAME;
		}

		userService.updateUserPassword(userId, password);
		LOG.info("Password successfully updated for user {}", user.getUsername());

		model.addAttribute(PASSWORD_RESET_ATTRIBUTE_NAME, "true");

		return CHANGE_PASSWORD_VIEW_NAME;

	}
}
