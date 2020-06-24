package com.eureka.data.vo;

public class ErrorVO {
    private final String reason;
    private final String errorCode;
    private final String message;

    public ErrorVO(final String reason, final String errorCode, final String message) {
        this.errorCode = errorCode;
		this.reason = reason;
        this.message = message;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }
}
