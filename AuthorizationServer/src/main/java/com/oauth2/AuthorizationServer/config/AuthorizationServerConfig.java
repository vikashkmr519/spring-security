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
		.authorizedGrantTypes("password")
		.and()
		.withClient("client2")
		.secret("secret2")
		.scopes("read")
		.authorizedGrantTypes("authorization_code")
		.redirectUris("http://localhost:9090/");
		//this redirect uri tells the authorisation sever
		//that where to send the token generated.
		// right now we have used fake uri
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authManager);
	}

	
}
