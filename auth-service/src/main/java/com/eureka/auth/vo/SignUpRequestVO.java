package com.eureka.auth.vo;

import java.util.HashSet;
import java.util.Set;

public class SignUpRequestVO {
	String username;
	String password;
	String email;
	Set<RoleName> roles = new HashSet<>();
	
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
	public void setRoles(Set<RoleName> roles) {
		this.roles = roles;
	}
}
