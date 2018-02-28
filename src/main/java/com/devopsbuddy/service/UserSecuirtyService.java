package com.devopsbuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRepository;

@Service
public class UserSecuirtyService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("loaduser");
		User user = userRepository.findByUsername(arg0);
		if (user == null) {
			throw new UsernameNotFoundException("UserName not found");
		}

		return user;
	}

}
