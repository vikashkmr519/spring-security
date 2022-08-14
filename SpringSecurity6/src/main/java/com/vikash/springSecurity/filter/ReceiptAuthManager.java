package com.vikash.springSecurity.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.vikash.springSecurity.provider.ReceiptAuthProvider;

@Component
public class ReceiptAuthManager implements AuthenticationManager{
	
	@Autowired
	ReceiptAuthProvider provider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return provider.authenticate(authentication);
	}

}
