package com.vikash.springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

import com.vikash.springSecurity.filter.MyCsrfFilter;

public class AppConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	MyCsrfFilter filter;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.csrfTokenRepository(new MyCustomCSRFToken()));
		http.addFilterAfter(filter, CsrfFilter.class);

	}
	
	

}
