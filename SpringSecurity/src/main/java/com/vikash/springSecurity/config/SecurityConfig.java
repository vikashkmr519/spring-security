package com.vikash.springSecurity.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.vikash.springSecurity.service.LoginUserDetailsService;

@SpringBootConfiguration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new LoginUserDetailsService();
	
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
