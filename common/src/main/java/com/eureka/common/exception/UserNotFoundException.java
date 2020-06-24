package com.eureka.common.exception;

import org.springframework.http.HttpStatus;

import com.eureka.common.constants.ErrorCodeConstants;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = -1855566715271304408L;
	
	public static final HttpStatus RESPONSE_STATUS = HttpStatus.NOT_FOUND;
	public static final String ERROR_CODE = ErrorCodeConstants.USER_NOT_FOUND;
	public static final String MESSAGE = "User not found";
}