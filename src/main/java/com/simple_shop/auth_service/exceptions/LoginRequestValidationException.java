package com.simple_shop.auth_service.exceptions;

public final class LoginRequestValidationException extends IllegalArgumentException {
	private static final long serialVersionUID = 3126015455269952391L;

	public LoginRequestValidationException() {
		super();
	}

	public LoginRequestValidationException(final String message) {
		super(message);
	}

	public LoginRequestValidationException(final Throwable throwable) {
		super(throwable);
	}

	public LoginRequestValidationException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
