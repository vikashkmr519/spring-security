package com.oauth2.AuthorizationServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@SpringBootConfiguration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
  
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
		.accessTokenValiditySeconds(5000)//setting the time for access token be valid or get expired
		.authorizedGrantTypes("password", "refresh_token")
		.and()
		.withClient("resourceserver")
		.secret("12345");
		// here we have added resource server as a client , because for opaque tokens , resource server calls the 
		// authorization server for checking the code, hence for that resource server need to be also a client of the 
		//auth server

		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints.authenticationManager(authManager);
		endpoints.userDetailsService(userDetailsService);
	}

	/*
	 * We have used BcryptPasswordEncoder in the config file hence while storing the secret which is a password
	 * we have to encrypt it. otherwise it will throw error.
	 * 
	 * Else we have option of using different Password Encoder for the client we will use here.
	 * Whiich can be done by setting the password encoder in the below method
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
				
			security.passwordEncoder(NoOpPasswordEncoder.getInstance())
			//.checkTokenAccess("permitAll()"); // to allow any one to access the /oauth/check_token endpoint
			.checkTokenAccess("isAuthenticated()"); // allowing only authenticated user to access the endpoint
	}
	
	

	
}
