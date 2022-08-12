package com.vikash.springSecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.vikash.springSecurity.filter.MyBasicCustomAuthenticationFilter;
import com.vikash.springSecurity.provider.MyAuthenticationProvider;

@SpringBootConfiguration
public class ApplicationConfig  extends WebSecurityConfigurerAdapter{

	Logger log = LoggerFactory.getLogger("MyAuthenticationProvider");
	
	@Autowired
	MyAuthenticationProvider provider;
	
	@Autowired
	MyBasicCustomAuthenticationFilter filter;
	


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.addFilterAt(filter, BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().authenticated();
			
	}
	
	
	
	
	

}
