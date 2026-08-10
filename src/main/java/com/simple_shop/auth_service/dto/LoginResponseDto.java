package com.simple_shop.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simple_shop.auth_service.dto.formatter.JsonLongFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {
	private boolean credentialsOk; // TODO De-/serialized correctly? What is accepted? Write myself?!

	@JsonLongFormatter
	private Long userId;

	private String errorMessage;

	public LoginResponseDto() {
		super();
	}

	public boolean getCredentialsOk() {
		return this.credentialsOk;
	}

	public LoginResponseDto setCredentialsOk(final boolean credentialsOk) {
		this.credentialsOk = credentialsOk;

		return this;
	}

	public Long getUserId() {
		return this.userId;
	}

	public LoginResponseDto setUserId(final Long userId) {
		this.userId = userId;

		return this;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public LoginResponseDto setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;

		return this;
	}
}