package com.vikash.springSecurity.provider;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.vikash.springSecurity.auth.SecretKeyAuthToken;
import com.vikash.springSecurity.repo.UserSecretKeyRepo;

@Component
public class SecretKeyAuthProvider implements AuthenticationProvider {
	Logger log = LoggerFactory.getLogger(SecretKeyAuthProvider.class);


	@Autowired
	UserSecretKeyRepo secretRepo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//here we directly called from repo class.
		var userOpt = secretRepo.findByUsername(authentication.getName());
		//idhar humey , username ke through keyy fetch hai and then header me paased key ko match krana hai database
	//se fetched key sey. Agr match hua toh authoried to work on it
		//yet to implement it here
		
		if(userOpt.isPresent()) {
			var user = userOpt.get();
			log.info("secret key is => "+user.getKeyy());
			if(authentication.getCredentials().equals(user.getKeyy())) {
			return new SecretKeyAuthToken(user.getUsername(),user.getKeyy() , List.of(()->"read"));
			}else {
				throw new BadCredentialsException("Secret key Didn't match");
			}
		}else {
			throw new BadCredentialsException("Failed OTP Authentication");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(SecretKeyAuthToken.class);
	}

}
