package com.eureka.data.bo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.eureka.data.model.UserInfo;
import com.eureka.data.repo.UserCredentialsRepository;

@Component
public class DataServiceBO {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceBO.class);
	
	@Autowired
    private UserCredentialsRepository userCredentialsRepoImpl;
	
    public void addUser(UserInfo user) throws Exception {
    	UserInfo potentialUser = userCredentialsRepoImpl.findByUsername(user.getUsername());
    	if(potentialUser != null && !potentialUser.getId().equals(user.getId())) {
    		logger.error("Username already exists");
    		//throw Exception
    	} else {
    		userCredentialsRepoImpl.save(user);
    	}
    }
    
    public UserInfo findUserById(String id) throws Exception {
        Optional<UserInfo> user = userCredentialsRepoImpl.findById(Long.parseLong(id));
        return user.isPresent() ? user.get() : null;
    }
    
    public UserInfo findUserByUsername(String username) throws Exception {
        Optional<UserInfo> user = Optional.ofNullable(userCredentialsRepoImpl.findByUsername(username));
        return user.isPresent() ? user.get() : null;
    }
}
