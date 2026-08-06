package com.simple_shop.auth_service.exceptions;

public final class InvalidRequestException extends IllegalArgumentException {
	private static final long serialVersionUID = 3126015455269952391L;

	public InvalidRequestException() {
		super();
	}

	public InvalidRequestException(final String message) {
		super(message);
	}

	public InvalidRequestException(final Throwable throwable) {
		super(throwable);
	}

	public InvalidRequestException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
