package com.eureka.auth.vo;

import java.util.List;

public class SignInResponseVO {
	String accesstoken;
	String tokenType;
	Long id;
	String username;
	String email;
	List<String> roles;
	
	public SignInResponseVO(String accessToken, String tokenType, List<String> roles, Long id, String username, String email) {
		super();
		this.accesstoken = accessToken;
		this.tokenType = tokenType;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
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
