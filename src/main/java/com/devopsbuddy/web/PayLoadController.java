package com.devopsbuddy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayLoadController {

	@RequestMapping(value="/payload")
	public String payload()
	{
		
		return "payload/payload";
	}
	
}
