package com.eureka.common.exception;

import org.springframework.http.HttpStatus;

import com.eureka.common.constants.ErrorCodeConstants;

public class UsernameExistsException extends Exception {
	private static final long serialVersionUID = -1855566715271304408L;
	
	public static final HttpStatus RESPONSE_STATUS = HttpStatus.CONFLICT;
	public static final String ERROR_CODE = ErrorCodeConstants.USERNAME_ALREADY_EXISTS;
	public static final String MESSAGE = "Username already exists";
}
