package com.vikash.springSecurity.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

public class MyCustomCSRFToken  implements CsrfTokenRepository{

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		CsrfToken csrf = new DefaultCsrfToken("X-X=CSRF-TOKEN", "_csrf", "GeneratedToken");
		return csrf;
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
