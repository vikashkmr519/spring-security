package com.vikash.springSecurity.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vikash.springSecurity.auth.SecretKeyAuthToken;
import com.vikash.springSecurity.auth.UsernamePasswordAuthToken;
import com.vikash.springSecurity.model.UserSecretKey;
import com.vikash.springSecurity.repo.ReceiptManager;
import com.vikash.springSecurity.repo.UserSecretKeyRepo;

@Component
public class UserPasswordAuthFilter extends OncePerRequestFilter {
	
	Logger log = LoggerFactory.getLogger("MyAuthenticationProvider");
	
	/*
	 * It will create an Authentication Object based on the request we go
	 * Then it will pass that object to the AuthenticationManager
	 */

	@Autowired
	MyAuthenticationManager manager;
	
	@Autowired
	UserSecretKeyRepo secretKeyRepo;
	
	@Autowired
	ReceiptManager receiptManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("do filter method of Custom filter class");
		
		
		String username = request.getHeader("uname");
		String password = request.getHeader("password");
		String key = request.getHeader("secret-key");
		Authentication auth ;		
		
		//agr first time login kr rahe hai, toh key null hogi , tab sirf username and password se login krenge
		if(key==null) {
			
			var objToken = new UsernamePasswordAuthToken(username, password);
			//delegate object to authentication manager

			 auth = manager.authenticate(objToken);
			
			//as key is null so we will first generate the secret key 
			UserSecretKey secretKey = new UserSecretKey();
			secretKey.setKeyy(findRandonKey());
			secretKey.setUsername(username);
			
			//save secret key in repo
			secretKeyRepo.save(secretKey);
			
		}else {
			var keyObject = new SecretKeyAuthToken(username,key);
			 auth = manager.authenticate(keyObject);
			 
			 //here imagine the Authorization key has been sent to database
			 //here we have stored in a Set
			String authToken = findRandonKey();
			receiptManager.add(authToken);
			
			//yaha se yeh token user ko bhi bhej diya
			response.setHeader("Authorization", authToken);

		}
		
		if(auth.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			filterChain.doFilter(request, response);
		}
				
		
	}
	
	public String findRandonKey() {
		return UUID.randomUUID().toString();
	
	}
	
	/*
	 * at filter run time it will not hit this below mentioned endpoint.
	 */

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		return !request.getServletPath().equals("/login");
	}
	
	
			
		
	

	
}
