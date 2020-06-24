package com.eureka.data.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.common.exception.EmailExistsException;
import com.eureka.common.exception.IncorrectFormatException;
import com.eureka.common.exception.UserNotFoundException;
import com.eureka.common.exception.UsernameExistsException;
import com.eureka.data.bo.DataServiceBO;
import com.eureka.data.model.User;
import com.eureka.data.vo.MessageVO;

@RestController
@CrossOrigin
public class DataServiceController {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceController.class);

	@Autowired
    private DataServiceBO dataServiceBO;
	
    @PostMapping(path = "/addUser", consumes = "application/json")
    public ResponseEntity<MessageVO> addUser(@Valid @RequestBody User user) throws UsernameExistsException,EmailExistsException,IncorrectFormatException {
    	logger.info("Starting to add user");
    	dataServiceBO.addUser(user);
    	logger.info("Added user with username and email: [{}, {}]", user.getUsername(), user.getEmail());
        return new ResponseEntity<>(new MessageVO("User added successfully"), HttpStatus.OK);
    }
    
    @GetMapping(path = "/findUserById")
    public ResponseEntity<User> findUserById(@RequestParam("id") String id) throws UserNotFoundException {
    	logger.info("Searching for user with id to add roles");
    	User user = dataServiceBO.findUserById(id);
    	logger.info("Found user with username and email: [{}, {}]", user.getUsername(), user.getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping(path = "/findUserByUsername")
    public ResponseEntity<User> findUserByUsername(@RequestParam("username") String username) throws UserNotFoundException {
    	logger.info("Looking up username: {}", username);
    	User user = dataServiceBO.findUserByUsername(username);
    	logger.info("Found user with username and email: [{}, {}]", user.getUsername(), user.getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
