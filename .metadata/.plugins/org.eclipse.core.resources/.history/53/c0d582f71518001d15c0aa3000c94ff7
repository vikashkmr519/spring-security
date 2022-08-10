package com.vikash.springSecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vikash.springSecurity.entity.Employee;
import com.vikash.springSecurity.entity.LoginUser;
import com.vikash.springSecurity.repo.EmployeeRepo;

public class LoginUserDetailsService implements UserDetailsService{
	
	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> empOpt = employeeRepo.findByUsername(username);
		empOpt.orElseThrow(()-> new UsernameNotFoundException("Employee Not Found"));
		return new LoginUser(empOpt.get());
	}

}
