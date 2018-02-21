package com.devopsbuddy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.devopsbuddy.config,com.devopsbuddy.web")
public class devopsbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(devopsbuddyApplication.class, args);
	}
}
