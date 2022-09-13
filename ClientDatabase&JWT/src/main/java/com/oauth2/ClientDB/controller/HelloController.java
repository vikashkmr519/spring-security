package com.oauth2.ClientDB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	/*
	 * Resource server is a place which provides some data or access, it should have 
	 * some api's to hit
	 */
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello... Welcome to resource Server";
	}

}
