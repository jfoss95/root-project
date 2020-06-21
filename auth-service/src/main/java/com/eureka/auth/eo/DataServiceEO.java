package com.eureka.auth.eo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eureka.auth.exception.ServiceException;
import com.eureka.auth.vo.UserVO;

@Component
public class DataServiceEO {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceEO.class);
	
	RestTemplate restTemplate = new RestTemplate();
	@Value("${services.rest.data-service.url}")
	String url;
	
	public UserVO getUserDetails(String username) throws ServiceException {
		logger.info("Retrieving user details for username: {}", username);
		try {
			ResponseEntity<UserVO> response = restTemplate.getForEntity(url+username, UserVO.class);
			if(response.getStatusCodeValue() == HttpStatus.OK.value() && response.hasBody()) {
					return response.getBody();
			} else {
				logger.error("There was a problem retrieving data from data-service");
				throw new ServiceException();
			}
		} catch(Exception e) {
			logger.error("There was a problem retrieving data from data-service", e);
			throw new ServiceException();
		}
	}
	
	public void addUser(UserVO userVO) throws ServiceException {
		logger.info("Creating user for username and email: ({}, {})", userVO.getUsername(), userVO.getEmail());
		try {
			ResponseEntity<UserVO> response = restTemplate.postForEntity(url, userVO, UserVO.class);
			if(response.getStatusCodeValue() != HttpStatus.OK.value()) {
				logger.error("There was a problem adding user data from data-service");
				throw new ServiceException();
			}
		} catch(Exception e) {
			logger.error("There was a problem adding user data from data-service", e);
			throw new ServiceException();
		}
	}
}
