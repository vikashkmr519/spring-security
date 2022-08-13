package com.vikash.springSecurity.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.vikash.springSecurity.auth.ReceiptAuthentication;
import com.vikash.springSecurity.repo.ReceiptManager;

@Component
public class ReceiptAuthProvider implements AuthenticationProvider {
	
	@Autowired
	ReceiptManager receiptManager;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String receiptNo = authentication.getName();
		boolean flag = receiptManager.contains(receiptNo);
		
		if(flag) {
			return new ReceiptAuthentication( receiptNo, null,null);
		}
		
		
		throw new BadCredentialsException("Failed !! Authorization please check token");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(ReceiptAuthentication.class);
	}

}
