package com.eureka.auth.eo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.eureka.common.exception.EmailExistsException;
import com.eureka.common.exception.IncorrectFormatException;
import com.eureka.common.exception.ServiceException;
import com.eureka.common.exception.UserNotFoundException;
import com.eureka.common.exception.UsernameExistsException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.eureka.auth.vo.ErrorVO;
import com.eureka.auth.vo.MessageVO;
import com.eureka.auth.vo.SignUpRequestVO;
import com.eureka.auth.vo.UserVO;
import com.eureka.common.constants.ErrorCodeConstants;

@Component
public class DataServiceEO {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceEO.class);
	
	RestTemplate restTemplate = new RestTemplate();
	@Value("${services.rest.data-service.getUser.url}")
	String getUserUrl;
	@Value("${services.rest.data-service.addUser.url}")
	String addUserUrl;
	
	public UserVO getUserDetails(String username) throws ServiceException, UserNotFoundException {
		logger.info("Retrieving user details for username: {}", username);
		try {
			ResponseEntity<UserVO> response = restTemplate.getForEntity(getUserUrl+username, UserVO.class);
			return response.getBody();
		} catch(HttpStatusCodeException e) {
			getUserDetailsError(e);
			return new UserVO();
		}
	}
	
	private void getUserDetailsError(HttpStatusCodeException e) throws ServiceException, UserNotFoundException {
		try {
			String responseString = e.getResponseBodyAsString();
	        ObjectMapper mapper = new ObjectMapper();
			ErrorVO result = mapper.readValue(responseString, ErrorVO.class);
			String errorCode = result.getErrorCode();
			if(errorCode.equals(ErrorCodeConstants.USER_NOT_FOUND)){
				throw new UserNotFoundException();
			} else {
				throw new ServiceException();
			}
		} catch (JsonProcessingException e1) {
			throw new ServiceException();
		}
	}
	
	public void addUser(SignUpRequestVO signUpRequestVO) throws ServiceException,
	UsernameExistsException, EmailExistsException, IncorrectFormatException {
		logger.info("Creating user for username and email: ({}, {})", signUpRequestVO.getUsername(), signUpRequestVO.getEmail());
		try {
			restTemplate.postForEntity(addUserUrl, signUpRequestVO, MessageVO.class);
		} catch (HttpStatusCodeException e) {
			addUserError(e);
        }
	}
	
	private void addUserError(HttpStatusCodeException e) throws ServiceException, 
	UsernameExistsException, EmailExistsException, IncorrectFormatException {
        try {
        	String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
			ErrorVO result = mapper.readValue(responseString, ErrorVO.class);
			String errorCode = result.getErrorCode();
			if(errorCode.equals(ErrorCodeConstants.USERNAME_ALREADY_EXISTS)){
				throw new UsernameExistsException();
			} else if(errorCode.equals(ErrorCodeConstants.EMAIL_ALREADY_EXISTS)) {
				throw new EmailExistsException();
			} else if(errorCode.equals(ErrorCodeConstants.INCORRECT_FORMAT)) {
				throw new IncorrectFormatException();
			} else {
				throw new ServiceException();
			}
		} catch (JsonProcessingException e1) {
			throw new ServiceException();
		}
	}
}
