package com.vikash.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.springSecurity.entity.User;
import com.vikash.springSecurity.service.MyUserService;

@RestController
public class HomeController {
	
	@Autowired
	MyUserService service;
	
	@GetMapping("/home")
	public String hello() {
		return "hello spring security";
				
	}
	
	@PostMapping("/createUser")
	public void addUser(@RequestBody User user) {
		 service.addUser(user);
	}
}
