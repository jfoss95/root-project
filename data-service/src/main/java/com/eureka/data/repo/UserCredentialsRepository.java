package com.eureka.data.repo;

import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

import com.eureka.data.model.UserInfo;

public interface UserCredentialsRepository extends CrudRepository<UserInfo, Long> {
	@Trace
	UserInfo findByUsername(String username);
}