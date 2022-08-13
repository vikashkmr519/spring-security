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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.vikash.springSecurity.filter.ReceiptAuthFilter;
import com.vikash.springSecurity.filter.UserPasswordAuthFilter;
import com.vikash.springSecurity.provider.ReceiptAuthProvider;
import com.vikash.springSecurity.provider.SecretKeyAuthProvider;
import com.vikash.springSecurity.provider.UserPasswordProvider;

@SpringBootConfiguration
public class ApplicationConfig  extends WebSecurityConfigurerAdapter{

	Logger log = LoggerFactory.getLogger(ApplicationConfig.class);
	
	
	@Autowired
	UserPasswordProvider provider;
	
	@Autowired
	ReceiptAuthProvider receiptProvider;
	
	@Autowired
	ReceiptAuthFilter receiptFilter;
	
	@Autowired
	SecretKeyAuthProvider authProvider;
	
	@Autowired
	UserPasswordAuthFilter authFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
		auth.authenticationProvider(authProvider);
		auth.authenticationProvider(receiptProvider);
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(authFilter,BasicAuthenticationFilter.class).addFilterBefore(receiptFilter, BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().authenticated();
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	
	
	

}
