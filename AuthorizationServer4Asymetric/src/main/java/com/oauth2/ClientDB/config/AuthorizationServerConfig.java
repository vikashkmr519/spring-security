package com.oauth2.ClientDB.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("client1").secret("secret1").scopes("read").authorizedGrantTypes("password");

	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(converter());
	}

	@Bean
	public JwtAccessTokenConverter converter() {
		var conv = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(
				new ClassPathResource("ssia.jks"),
				"ssia123".toCharArray());
		
		conv.setKeyPair(keyFactory.getKeyPair("ssia"));
		return conv;
	}
	//
	// here we are not passing the signingKey due to using asymetric way
	
	//we have created a keyStoreFactory and storing the private key  in the file ssia.jks, whcib is
	//right now not present in this project but we have to generate private key somehow and store
	//it in that file and keep that file in class path , that is in resource folder.
	//and ssia123 is the secret password for this key to work properly
	
	//and the resource serever will have the conv.setVerifierKey(publicKey); where public key will be stored
	//and that public key is also generated as private key is generated

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(converter());
	}

}
