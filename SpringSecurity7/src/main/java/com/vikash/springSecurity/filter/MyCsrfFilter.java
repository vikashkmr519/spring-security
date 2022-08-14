package com.vikash.springSecurity.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

public class MyCsrfFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		CsrfToken ob = (CsrfToken) request.getAttribute("_csrf");
		ob.getHeaderName();
		ob.getParameterName();
		ob.getToken();
		
		filterChain.doFilter(request, response);
		
	}

}
