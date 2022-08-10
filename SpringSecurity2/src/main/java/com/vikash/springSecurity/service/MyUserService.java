package com.vikash.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.vikash.springSecurity.entity.User;

@Service
public class MyUserService {
	
	@Autowired
	UserDetailsManager manager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void addUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		manager.createUser(user);
	}

}
