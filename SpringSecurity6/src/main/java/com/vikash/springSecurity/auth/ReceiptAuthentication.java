package com.vikash.springSecurity.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;



public class ReceiptAuthentication  extends UsernamePasswordAuthToken{

	public ReceiptAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
			}
	
	public ReceiptAuthentication(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities) {
		super(principal,credentials,authorities);
	}


}
