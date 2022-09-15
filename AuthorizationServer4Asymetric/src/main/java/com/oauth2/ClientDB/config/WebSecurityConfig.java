package com.oauth2.ClientDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService uds() {
		var uds = new InMemoryUserDetailsManager();
		
		var  u1 = User.withUsername("john")
				.password("12345")
				.authorities("read")
				.build();
		
		uds.createUser(u1);
		
		return uds;
	}

	

	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
				return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	

}
