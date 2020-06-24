package com.eureka.common.exception;

import org.springframework.http.HttpStatus;

import com.eureka.common.constants.ErrorCodeConstants;

public class EmailExistsException extends Exception {
	private static final long serialVersionUID = -1855566715271304408L;
	
	public static final HttpStatus RESPONSE_STATUS = HttpStatus.CONFLICT;
	public static final String ERROR_CODE = ErrorCodeConstants.EMAIL_ALREADY_EXISTS;
	public static final String MESSAGE = "Email already exists";
}
