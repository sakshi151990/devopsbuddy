package com.devopsbuddy;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.persistence.Role;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRole;
import com.devopsbuddy.service.UserService;
import com.devopsbuddy.utils.UserUtil;

@SpringBootApplication
@ComponentScan(basePackages = "com.devopsbuddy.web,com.devopsbuddy.service,com.devopsbuddy.config,com.devopsbuddy.persistence")
public class devopsbuddyApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(devopsbuddyApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("inside run");
		User user = UserUtil.createBasicUser();
		System.out.println(user);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RoleEnum.ADMIN)));
		System.out.println(userService);
		userService.CreateUser(user, PlansEnum.PRO, userRoles);

	}

}
