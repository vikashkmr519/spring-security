package com.vikash.springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;




public class MyCustAuthProvider implements AuthenticationProvider{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/*
	 * authenticate method is used to authenticate user and then return the data of user , username and password
	 * 
	 * This method has 3 work
	 * 1) Authenticate user from authentication parameter
	 * 2) if error occur or invalid user throw exception
	 * 3) If it gets confused to authenticate the user then returns null
	 */

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		System.out.println(password);
		//get the user from database
		UserDetails user = userDetailsService.loadUserByUsername(username);
		
		//check user is not null, then whether the user retrieved password, matches the above password
		if(user != null && passwordEncoder.matches(password,user.getPassword())) {
			
			return new UsernamePasswordAuthenticationToken(user.getUsername(),password);
		}
		
		return  (Authentication) new BadCredentialsException("Error !!");
	}

	/*
	 * supports method tells which type of authetication provider we are using
	 */
	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
