package com.eureka.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Region("role")
@RequiredArgsConstructor(staticName="newRole")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@Indexed
    private RoleName name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
}