package com.eureka.auth.vo;

public class ErrorVO {
    private final String reason;
    private final String message;

    public ErrorVO(final String reason, final String message) {
        this.reason = reason;
        this.message = message;
    }

	public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }
}
