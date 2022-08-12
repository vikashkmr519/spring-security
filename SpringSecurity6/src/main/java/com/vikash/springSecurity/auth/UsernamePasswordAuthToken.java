package com.vikash.springSecurity.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class UsernamePasswordAuthToken extends UsernamePasswordAuthenticationToken {
	/*
	 * Here we have extended Token class but in real world project we will implement
	 * the base interface that is Authentication and will implement its method. Here
	 * just for the easyness we have extended an already implemented class for this
	 * token. TO know more check what it is implementing from. etc.
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -6804138884471560184L;

	public  UsernamePasswordAuthToken(Object principal, Object credentials) {
		super(principal,credentials);
		
		
	}
	
	public UsernamePasswordAuthToken(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities) {
		super(principal,credentials,authorities);
	}



}
