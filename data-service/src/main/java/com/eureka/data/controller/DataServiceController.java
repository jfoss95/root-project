package com.eureka.data.controller;

import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.data.bo.DataServiceBO;
import com.eureka.data.exception.DataServiceException;
import com.eureka.data.exception.EmailExistsException;
import com.eureka.data.exception.IncorrectFormatException;
import com.eureka.data.exception.RoleExistsException;
import com.eureka.data.exception.RoleNotFoundException;
import com.eureka.data.exception.UserNotFoundException;
import com.eureka.data.exception.UsernameExistsException;
import com.eureka.data.model.Role;
import com.eureka.data.model.RoleName;
import com.eureka.data.model.User;

@RestController
@CrossOrigin
public class DataServiceController {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceController.class);

	@Autowired
    private DataServiceBO dataServiceBO;
	
    @PostMapping(path = "/addUser", consumes = "application/json")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user) throws UsernameExistsException,EmailExistsException,IncorrectFormatException {
    	dataServiceBO.addUser(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
    @PostMapping(path = "/addRoles", consumes = "application/json")
    public ResponseEntity<String> addRoles(@Valid @RequestBody Set<Role> roles) throws RoleExistsException,IncorrectFormatException {
    	dataServiceBO.addRoles(roles);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
    @GetMapping(path = "/findUserById")
    public ResponseEntity<User> findUserById(@RequestParam("id") String id) throws UserNotFoundException {
        User user = dataServiceBO.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping(path = "/findUserByUsername")
    public ResponseEntity<User> findUserByUsername(@RequestParam("username") String username) throws UserNotFoundException {
    	logger.info("Looking up username: {}", username);
    	User user = dataServiceBO.findUserByUsername(username);
    	logger.info("TEST---User's roles are: {}", user.getRoles());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping(path = "/getAllRoles")
    public ResponseEntity<Set<RoleName>> getAllRoles() throws RoleNotFoundException {
    	logger.info("Looking up all existing roles");
    	Set<RoleName> roles = dataServiceBO.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
