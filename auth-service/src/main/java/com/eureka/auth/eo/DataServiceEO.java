package com.eureka.auth.eo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eureka.auth.constants.AuthServiceConstants;
import com.eureka.data.vo.UserCredentialsVO;

@Component
public class DataServiceEO {
	private static final Logger logger = LoggerFactory.getLogger(DataServiceEO.class);
	
	RestTemplate restTemplate = new RestTemplate();
	@Value("${services.rest.data-service.url}")
	String url;
	
	public UserCredentialsVO getUserDetails(String username) {
		logger.info("---USERNAME: "+username);
		UserCredentialsVO userResponseVO = null;
		ResponseEntity<UserCredentialsVO> response = restTemplate.getForEntity(url+username, UserCredentialsVO.class);
		if(response.getStatusCodeValue() == AuthServiceConstants.HTTP_STATUS_OK) {
			if(response.hasBody()) {
				userResponseVO = response.getBody();
			} else {
				logger.warn("There is no such user in the database");
			}
		}
		return userResponseVO;
	}
}
