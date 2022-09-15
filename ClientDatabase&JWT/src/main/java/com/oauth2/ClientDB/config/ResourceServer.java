package com.oauth2.ClientDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServer {

	/*
	 * both below method should have been used if Resource server was different project or microservice
	 * and not imolemented in the same porject as in AuthServer
	 */
	
	
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(converter());
//	}
//	
//	@Bean
//	public JwtAccessTokenConverter converter() {
//		var conv = new JwtAccessTokenConverter();
//		conv.setSigningKey("secret");
//		return conv;
//	}

}
