package com.devopsbuddy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	
	public static final String LOGIN_VIEW_NAME="user/login";
	
	@RequestMapping(value="/login")
	public String login()
	{
		System.out.println("inside login controller");
		return LOGIN_VIEW_NAME;
	}
	
	
}
