package com.eureka.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Region("user")
@RequiredArgsConstructor(staticName="newUser")
public class UserInfo {
	@NonNull
	@Id
	private Long id;
	@NonNull
	@Indexed
    private String username;
	@NonNull
	@Indexed
    private String password;
	@NonNull
	@Indexed
    private String role;

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
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}