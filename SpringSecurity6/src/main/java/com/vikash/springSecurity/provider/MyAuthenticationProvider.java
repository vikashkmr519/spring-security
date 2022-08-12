package com.vikash.springSecurity.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.vikash.springSecurity.filter.MyCustAuthenticationToken;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	Logger log = LoggerFactory.getLogger("MyAuthenticationProvider");
	
	@Value("${secret_key}")
	String secretKey;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("inside athenticate method of authentocat"
				+ "ion provider");
		if(secretKey.equals(authentication.getName())) {
			//this 3 parameter constructor inetrnally is making setAuthenticated(true);
			//otherwise if we don't do that then we have to seperately make it to be authenticated
			var authObj = new MyCustAuthenticationToken(null,null,null);
			
			log.info("Returned authencation Object back to manager");
			return authObj;
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(MyCustAuthenticationToken.class);
	}

}
