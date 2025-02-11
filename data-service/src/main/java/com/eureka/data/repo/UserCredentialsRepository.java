package com.eureka.data.repo;

import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

import com.eureka.data.model.User;

public interface UserCredentialsRepository extends CrudRepository<User, Long> {
	@Trace
	User findByUsername(String username);
	
	@Trace
	User findByEmail(String email);
	
	@Trace
	@Query("select max(id) from /user")
	Long findHighestId();
}