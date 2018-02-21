package com.devopsbuddy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CopyController {

	@RequestMapping("/about")
	public String aboutpage()
	{
		
		return "copy/about";
	}
	
	
}
