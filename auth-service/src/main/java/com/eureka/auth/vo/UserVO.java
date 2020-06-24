package com.eureka.auth.vo;

import java.util.HashSet;
import java.util.Set;

public class UserVO {
	private Long id;
    private String username;
    private String password;
    private String email;
	private Set<RoleName> roles = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleName> getRoles() {
		return roles;
	}

	/*
	public void setRoles(Set<RoleName> roles) {
		//this.roles = roles;
	}
	*/
	public void setRoles(Set<RoleName> roles) {
		this.roles = roles;
	}
}