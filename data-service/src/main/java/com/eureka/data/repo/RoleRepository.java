package com.eureka.data.repo;

import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

import com.eureka.data.model.Role;
import com.eureka.data.model.RoleName;

public interface RoleRepository extends CrudRepository<Role, Long> {
	@Trace
	Role findByName(RoleName name);
	
	@Trace
	Boolean existsByName(RoleName name);
}