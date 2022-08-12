package com.vikash.springSecurity.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vikash.springSecurity.auth.UsernamePasswordAuthToken;
import com.vikash.springSecurity.service.MyUserDetails;
import com.vikash.springSecurity.service.MyUserDetailsService;

@Component
public class UserPasswordProvider implements AuthenticationProvider {
	Logger log = LoggerFactory.getLogger(UserPasswordProvider.class);
	
	@Autowired
	MyUserDetailsService userDetailService;

	@Autowired
	PasswordEncoder encoder; 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("Inside authenticate method of UserPasswordProvider");
		
		var user = userDetailService.loadUserByUsername(authentication.getName());
		
		if(user!=null && encoder.matches( (String)authentication.getCredentials(),user.getPassword() )) {
			
			log.info("user found successfully in database and returned back to filter class");
			return new UsernamePasswordAuthToken(user.getUsername(), user.getPassword(),user.getAuthorities());
		}
				
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthToken.class);
	}

}
