package com.vikash.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserManageConfig {

	
	
	@Bean
	public UserDetailsService userDetailsService() {
		var user = new InMemoryUserDetailsManager();
		
		user.createUser(User.withUsername("Mark").password("1234").authorities("read").build());
		return user;
	}
	
	public PasswordEncoder passwordEncoder() {
		//use Bcrypt in production
		return NoOpPasswordEncoder.getInstance();
	}

}
