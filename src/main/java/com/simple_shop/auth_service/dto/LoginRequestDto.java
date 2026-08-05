package com.simple_shop.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequestDto {
	private String username;

	private String passwordHash;

	private String clientType;

	public LoginRequestDto() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public LoginRequestDto setUsername(final String username) {
		this.username = username;

		return this;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public LoginRequestDto setPasswordHash(final String passwordHash) {
		this.passwordHash = passwordHash;

		return this;
	}

	public String getClientType() {
		return this.clientType;
	}

	public LoginRequestDto setClientType(final String clientType) {
		this.clientType = clientType;

		return this;
	}
}