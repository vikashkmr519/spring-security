package com.vikash.springSecurity.filter;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class MyCustAuthenticationToken extends AbstractAuthenticationToken {
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

	private final Object principal;

	private Object credentials;

	public MyCustAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
		
	}
	
	public MyCustAuthenticationToken(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); // must
	}

	@Override
	public Object getCredentials() {

		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}


}
