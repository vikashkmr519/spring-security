package com.oauth2.AuthorizationServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@SpringBootConfiguration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authManager;
	/*
	 * DIFFERENT GRANT TYPES
	 * authorization_code
	 * password ----> deprecated
	 * client credentials
	 * refresh token
	 * implicit ------> deprecated
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("client1")
		.secret("secret1")
		.scopes("read")
		.authorizedGrantTypes("password", "refresh_token")
		.and()
		.withClient("client2")
		.secret("secret2")
		.scopes("read")
		.authorizedGrantTypes("authorization_code", "refresh_token")
		.redirectUris("http://localhost:9090/")
		.and()
		.withClient("client3")
		.secret("secret3")
		.scopes("read")
		.authorizedGrantTypes("client_credentials");
		

		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authManager);
	}

	
}
