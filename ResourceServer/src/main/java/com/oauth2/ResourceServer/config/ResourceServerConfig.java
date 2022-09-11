package com.oauth2.ResourceServer.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@SpringBootConfiguration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	/*
	 * for this Resource server, AuthorizationServer3 is the package for Auth server
	 */
	
	@Autowired
	private DataSource datasource;
	
	
	/*
	 * This is required if we are using black boarding concept,
	 * when tokens are stored or fetched from the data base
	 * 
	 * 
	 * AuthrisarionServer would also have stored the token in the database
	 * and resource server will fetch the token from the database.
	 * Just provide database url ,username password, and go ahead
	 * 
	 * But need to tell the tokenStore that we are going to use database.
	 */
	@Autowired
	public TokenStore tokenStore() {
		return new JdbcTokenStore(datasource);
	}


	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}
	
	
	
}
