package com.vikash.springSecurity.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MyBasicCustomAuthenticationFilter extends OncePerRequestFilter {
	
	Logger log = LoggerFactory.getLogger("MyAuthenticationProvider");
	
	/*
	 * It will create an Authentication Object based on the request we go
	 * Then it will pass that object to the AuthenticationManager
	 */

	@Autowired
	MyAuthenticationManager manager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("do filter method of Custom filter class");
		
		
		String authObj = request.getHeader("auth_key");
		log.info("auth_key = "+authObj);
		
		
		//create authentication object
		var objToken = new MyCustAuthenticationToken(authObj, null);
		
		try {
			log.info("inside try catch");
		//delegate object to authentication manager
		//manager later on delegate to provider and returns principle
		var authPrincipal = manager.authenticate(objToken);
		
		log.info("authPrincipal is authenticated = "+ authPrincipal.isAuthenticated());
		
		//for future use save the principal in SecurityContext
		if(authPrincipal.isAuthenticated()) {
			log.info("authencticated if condition");
			SecurityContextHolder.getContext().setAuthentication(authPrincipal);
			filterChain.doFilter(request, response);
		}
		}catch(AuthenticationException e)
		{
			throw new BadCredentialsException("Invalid Principal");
		}
		
	}
			
		
	

	
}
