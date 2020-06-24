package com.eureka.common.exception;

import org.springframework.http.HttpStatus;

import com.eureka.common.constants.ErrorCodeConstants;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -8595258076560418331L;
	
	public static final HttpStatus RESPONSE_STATUS = HttpStatus.SERVICE_UNAVAILABLE;
	public static final String ERROR_CODE = ErrorCodeConstants.SERVICE_INVOCATION;
	public static final String MESSAGE = "Problem connecting to service";
}
