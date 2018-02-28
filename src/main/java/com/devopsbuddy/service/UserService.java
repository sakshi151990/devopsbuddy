package com.devopsbuddy.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.persistence.Plan;
import com.devopsbuddy.persistence.PlanRepository;
import com.devopsbuddy.persistence.RoleRepository;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRepository;
import com.devopsbuddy.persistence.UserRole;

@Service
@Transactional
public class UserService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordencoder;

	@Transactional
	public User CreateUser(User user, PlansEnum planenum, Set<UserRole> userroles) {

		System.out.println("insied service");
		String encryptedpassword = passwordencoder.encode(user.getPassword());
		user.setPassword(encryptedpassword);
		Plan plan = new Plan(planenum);
		if (!planRepository.exists(planenum.getId())) {
			plan = planRepository.save(plan);
		}

		user.setPlan(plan);

		for (UserRole role : userroles) {
			roleRepository.save(role.getRole());
		}

		user.getUserroles().addAll(userroles);
		user = userRepository.save(user);
		return user;
	}

}
