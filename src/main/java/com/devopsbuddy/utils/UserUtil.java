package com.devopsbuddy.utils;

import com.devopsbuddy.persistence.User;

public class UserUtil {

	private UserUtil() {
	}

	public static User createBasicUser() {

		System.out.println("insied utils");
		User user = new User();
		user.setCountry("India");
		user.setUsername("user");
		user.setDescription("basicplan and role");
		user.setEmail("sakshi@gmail.com");
		user.setFirstName("sakshi");
		user.setLastName("ag");
		user.setPassword("password");
		user.setPhone("6789098");
		user.setProfile_img("http:bla.com");
		user.setStripecustomerid("234");
		user.setEnabled(true);
		return user;

	}

}
