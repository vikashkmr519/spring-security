package com.vikash.springSecurity.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser  implements UserDetails{
	
	/*
	 * We implemented this different class for UserDetails because, of Solid property
	 * Single Responsibility code
	 * Employee class is acting as an entity so we don't want any one to get confused
	 * in future after seeing the class. Hence we did not implemented this UserDetails interface for employee class
	 * and created one separate class for holding its methods. 
	 */

	
	private final  Employee emp;
	
	@Autowired
	public LoginUser(Employee emp) {
		this.emp = emp;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(()->"read");
	}

	@Override
	public String getPassword() {
	return emp.getPassword();
	}

	@Override
	public String getUsername() {
		return emp.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
