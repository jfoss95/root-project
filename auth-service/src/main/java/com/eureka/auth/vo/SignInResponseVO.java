package com.eureka.auth.vo;

import java.util.List;

public class SignInResponseVO {
	String jwt;
	Long id;
	String username;
	String email;
	List<String> roles;
	
	public SignInResponseVO(String jwt, List<String> roles, Long id, String username, String email) {
		super();
		this.jwt = jwt;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
