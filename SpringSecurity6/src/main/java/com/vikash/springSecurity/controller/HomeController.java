package com.vikash.springSecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/home")
	public String hello(Authentication auth) {
		log.info(auth.getName());
		/*
		 * another way of accessing auth value any where
		 * if we will not pass it to the argument
		 *Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 */
		return "hello username => "+auth.getName();
				
	}

}
