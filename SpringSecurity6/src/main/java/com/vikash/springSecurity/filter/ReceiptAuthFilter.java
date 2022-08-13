package com.vikash.springSecurity.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vikash.springSecurity.auth.ReceiptAuthentication;

@Component
public class ReceiptAuthFilter extends OncePerRequestFilter{

	
	@Autowired
	private MyAuthenticationManager authenticationManager;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var receiptNo = request.getHeader("Authorization");
		
		Authentication authentication = new ReceiptAuthentication(receiptNo,null);
		
		var a = authenticationManager.authenticate(authentication);
		
		SecurityContextHolder.getContext().setAuthentication(a);
		filterChain.doFilter(request, response);
		
	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	
			return request.getServletPath().equals("/login");
	}
	
	

}
