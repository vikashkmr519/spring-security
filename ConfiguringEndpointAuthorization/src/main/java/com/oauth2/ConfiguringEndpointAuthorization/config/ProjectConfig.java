package com.oauth2.ConfiguringEndpointAuthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig  extends WebSecurityConfigurerAdapter{

	
	@Bean
	public UserDetailsService userDetailsService() {
		
		var uds = new InMemoryUserDetailsManager();
		
		var u1 = User.withUsername("bill")
				.password("12345")
				.authorities("manager")
				.build();
		
		var u2 = User.withUsername("john")
				.password("12345")
				.authorities("admin")
				.build();
		
		uds.createUser(u1);
		uds.createUser(u2);
		
		return uds;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		
		http.authorizeRequests()
			   .anyRequest().hasAnyRole("ROLE_manager","ROLE_admin"); //it will say any request can be allowed without any authentication
				
	}			
	
	/*
	 * 
	 * http.authorizeRequests()
			   .anyRequest().permitAll(); 
			   //it will say any request can be allowed without any authentication
			    * 
		http.authorizeRequests()
			   .anyRequest().denyAll();// deny all endpoints
			   
			   http.authorizeRequests()
			   .anyRequest().hasAuthority("admin");// only admin authority can authorize if authenticated
			
				
		http.authorizeRequests()
			   .anyRequest().hasAnyAuthority("admin","manager");// any authority can now access
			   
			   http.authorizeRequests()
			   .anyRequest().hasAnyRole("ROLE_manager","ROLE_admin");
			   //in this case above while creating user in authorities also we have to pass
			    * ROLE_manager and ROLE_admin
	 */
	
	
}
