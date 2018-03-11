package com.devopsbuddy.utils;

import javax.servlet.http.HttpServletRequest;

import com.devopsbuddy.persistence.User;
import com.devopsbuddy.web.ForgotMyPasswordContorller;
import com.devopsbuddy.web.basicAccountPayload;

public class UserUtil {

	private UserUtil() {
	}

	public static User createBasicUser(String username, String email) {

		System.out.println("insied utils");
		User user = new User();
		user.setCountry("India");
		user.setUsername(username);
		user.setDescription("basicplan and role");
		user.setEmail(email);
		user.setFirstName("sakshi");
		user.setLastName("ag");
		user.setPassword("password");
		user.setPhone("6789098");
		user.setProfile_img("http:bla.com");
		user.setStripecustomerid("234");
		user.setEnabled(true);
		return user;

	}

	public static String createPasswordResetURL(HttpServletRequest request, long userId, String token) {

		String passwordResetUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + ForgotMyPasswordContorller.CHANGE_PASSWORD_PATH + "?id=" + userId
				+ "&token=" + token;
		return passwordResetUrl;

	}

	public static <T extends basicAccountPayload> User fromWebUserToDomainUser(T frontendPayload) {
		User user = new User();
		user.setUsername(frontendPayload.getUsername());
		user.setPassword(frontendPayload.getPassword());
		user.setFirstName(frontendPayload.getFirstName());
		user.setLastName(frontendPayload.getLastName());
		user.setEmail(frontendPayload.getEmail());
		user.setPhone(frontendPayload.getPhoneNumber());
		user.setCountry(frontendPayload.getCountry());
		user.setEnabled(true);
		user.setDescription(frontendPayload.getDescription());

		return user;
	}

}
