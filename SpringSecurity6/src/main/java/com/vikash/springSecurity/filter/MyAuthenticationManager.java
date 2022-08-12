package com.vikash.springSecurity.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.vikash.springSecurity.provider.UserPasswordProvider;

@Component
public class MyAuthenticationManager implements AuthenticationManager {
	
	@Autowired
	UserPasswordProvider provider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
	return 	provider.authenticate(authentication);
		
	}

}
