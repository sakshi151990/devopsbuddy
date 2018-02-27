package com.devopsbuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.devopsbuddy.persistence.PlanRepository;

@ComponentScan(basePackages = { "com.devopsbuddy.config", "com.devopsbuddy.web", "com.devopsbuddy.persistence",
		"com.devopsbuddy.service" })
@SpringBootApplication
public class devopsbuddyApplication {

	@Autowired
	public static PlanRepository planrepo;

	public static void main(String[] args) {
		SpringApplication.run(devopsbuddyApplication.class, args);

		System.out.println(planrepo);

	}
}
