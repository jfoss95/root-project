package com.eureka.data.bo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eureka.common.exception.EmailExistsException;
import com.eureka.common.exception.IncorrectFormatException;
import com.eureka.common.exception.UserNotFoundException;
import com.eureka.common.exception.UsernameExistsException;
import com.eureka.data.model.RoleName;
import com.eureka.data.model.User;
import com.eureka.data.repo.UserCredentialsRepository;

@Component
public class DataServiceBO {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceBO.class);
	
	@Autowired
    private UserCredentialsRepository userCredentialsRepoImpl;
	
    public void addUser(User user) throws UsernameExistsException, EmailExistsException, IncorrectFormatException {
    	if(user == null) {
    		logger.error("Incorrect format");
    		throw new IncorrectFormatException();
    	}
    	if(Boolean.TRUE.equals(existsByUsername(user.getUsername()))) {
    		logger.error("Username already exists");
    		throw new UsernameExistsException();
    	}
    	if(Boolean.TRUE.equals(existsByEmail(user.getEmail()))) {
    		logger.error("Email already exists");
    		throw new EmailExistsException();
    	}
    	if(!Optional.ofNullable(user.getId()).isPresent()) {
    		logger.info("generating id for user with username and email: [{}, {}]", user.getUsername(), user.getEmail());
    		user.setId(generateNewId());
    	}
    	if(!Optional.ofNullable(user.getRoles()).isPresent() || user.getRoles().isEmpty()) {
    		Set<RoleName> roles = new HashSet<>();
    		roles.add(RoleName.ROLE_USER);
    		user.setRoles(roles);
    	}
    	userCredentialsRepoImpl.save(user);
    	logger.info("Saved user with username and email: ({}, {})", user.getUsername(), user.getEmail());
    }
    
    private Long generateNewId() {
    	Long newId = (long) 1;
		Optional<Long> id = Optional.ofNullable(userCredentialsRepoImpl.findHighestId());
		if(id.isPresent()) {
			newId = id.get();
			return ++newId;
		} else {
			return newId;
		}
	}

	public User findUserById(String id) throws UserNotFoundException {
    	Optional<User> user = userCredentialsRepoImpl.findById(Long.parseLong(id));
    	if(user.isPresent()) {
    		return user.get();
    	} else {
    		logger.error("User not found");
    		throw new UserNotFoundException();
    	}
	}
    
    public User findUserByUsername(String username) throws UserNotFoundException {
    	Optional<User> user = Optional.ofNullable(userCredentialsRepoImpl.findByUsername(username));
    	if(user.isPresent()) {
    		return user.get();
    	} else {
    		logger.error("User not found");
    		throw new UserNotFoundException();
    	}
    }
    
    Boolean existsByUsername(String username) {
		return Optional.ofNullable(userCredentialsRepoImpl.findByUsername(username)).isPresent();
	}
	
	Boolean existsByEmail(String email) {
		return Optional.ofNullable(userCredentialsRepoImpl.findByEmail(email)).isPresent();
	}
}
