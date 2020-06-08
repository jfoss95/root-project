package com.eureka.data.controller;

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

import com.eureka.data.bo.DataServiceBO;
import com.eureka.data.model.UserInfo;

@RestController
@CrossOrigin
public class DataServiceController {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceController.class);

	@Autowired
    private DataServiceBO dataServiceBO;
	
    @PostMapping(path = "/addUser", consumes = "application/json")
    public ResponseEntity<String> addUser(@RequestBody UserInfo user) throws Exception {
    	dataServiceBO.addUser(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
    @GetMapping(path = "/findUserById")
    public ResponseEntity<UserInfo> findUserById(@RequestParam("id") String id) throws Exception {
        UserInfo user = dataServiceBO.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping(path = "/findUserByUsername")
    public ResponseEntity<UserInfo> findUserByUsername(@RequestParam("username") String username) throws Exception {
    	logger.info("Looking up username: {}", username);
    	UserInfo user = dataServiceBO.findUserByUsername(username);
    	logger.info("TEST---User role is: {}", user.getRole());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
