package com.eureka.data.bo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eureka.data.exception.EmailExistsException;
import com.eureka.data.exception.IncorrectFormatException;
import com.eureka.data.exception.RoleExistsException;
import com.eureka.data.exception.RoleNotFoundException;
import com.eureka.data.exception.UserNotFoundException;
import com.eureka.data.exception.UsernameExistsException;
import com.eureka.data.model.Role;
import com.eureka.data.model.RoleName;
import com.eureka.data.model.User;
import com.eureka.data.repo.RoleRepository;
import com.eureka.data.repo.UserCredentialsRepository;

@Component
public class DataServiceBO {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceBO.class);
	
	@Autowired
    private UserCredentialsRepository userCredentialsRepoImpl;
	
	@Autowired
	private RoleRepository roleRepoImpl;
	
    public void addUser(User user) throws UsernameExistsException, EmailExistsException, IncorrectFormatException {
    	if(user == null) {
    		logger.error("Incorrect format");
    		throw new IncorrectFormatException();
    	}
    	if(Boolean.TRUE.equals(userCredentialsRepoImpl.existsByUsername(user.getUsername()))) {
    		logger.error("Username already exists");
    		throw new UsernameExistsException();
    	}
    	if(Boolean.TRUE.equals(userCredentialsRepoImpl.existsByEmail(user.getEmail()))) {
    		logger.error("Email already exists");
    		throw new EmailExistsException();
    	}
    	userCredentialsRepoImpl.save(user);
    	logger.info("Saved user with username and email: ({}, {})", user.getUsername(), user.getEmail());
    }
    
    public void addRoles(Set<Role> roles) throws RoleExistsException, IncorrectFormatException {
    	if(roles == null) {
    		logger.error("Incorrect format for roles");
    		throw new IncorrectFormatException();
    	}
    	Set<Role> potentialRoles = new HashSet<>();
    	for(Role role: roles) {
    		if(Boolean.TRUE.equals(roleRepoImpl.existsByName(role.getName()))) {
    			logger.warn("The role {} already exists", role.getName());
    		} else {
    			potentialRoles.add(role);
    		}
    	}
    	if(potentialRoles.isEmpty()) {
    		throw new RoleExistsException();
    	} else {
    		roleRepoImpl.saveAll(roles);
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
    
    public Set<RoleName> getAllRoles() throws RoleNotFoundException {
    	Set<RoleName> roleResults = new HashSet<>();
    	Iterable<Role> roles = roleRepoImpl.findAll();
		Iterator<Role> addRoles = roles.iterator();
		while(addRoles.hasNext()) {
			Role role = addRoles.next();
			roleResults.add(role.getName());
		}
    	if(!roleResults.isEmpty()) {
    		logger.info("All roles found are: {}", roleResults);
    		return roleResults;
    	} else {
    		logger.error("No roles found");
    		throw new RoleNotFoundException();
    	}
    }
}
