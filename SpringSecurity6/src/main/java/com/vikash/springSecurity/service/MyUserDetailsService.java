package com.vikash.springSecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vikash.springSecurity.model.User;
import com.vikash.springSecurity.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optUser =  this.userRepo.findByUsername(username);
		
		User user = optUser.get();
		MyUserDetails userDetails = new MyUserDetails();
		userDetails.setPassword(user.getPassword());
		userDetails.setUid(user.getUid());
		userDetails.setUsername(user.getUsername());
		return userDetails;

	}

}
