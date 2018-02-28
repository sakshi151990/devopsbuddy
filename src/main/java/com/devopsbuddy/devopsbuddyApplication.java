package com.devopsbuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.devopsbuddy.persistence.PlanRepository;
import com.devopsbuddy.service.UserService;

@SpringBootApplication
@ComponentScan(basePackages = "com.devopsbuddy.config, com.devopsbuddy.web, com.devopsbuddy.persistence,com.devopsbuddy.service")
public class devopsbuddyApplication implements CommandLineRunner {

	@Autowired
	public static PlanRepository planrepo;

	@Autowired
	public static UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(devopsbuddyApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * System.out.println("inside run"); User user = UserUtil.createBasicUser();
	 * 
	 * Set<UserRole> userRoles = new HashSet<>(); userRoles.add(new UserRole(user,
	 * new Role(RoleEnum.ADMIN)));
	 * 
	 * userService.CreateUser(user, PlansEnum.PRO, userRoles);
	 * 
	 * }
	 */
}
