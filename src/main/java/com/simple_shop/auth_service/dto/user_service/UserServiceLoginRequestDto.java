package com.simple_shop.auth_service.dto.user_service;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserServiceLoginRequestDto {
	private String username;

	private String passwordHash;

	public UserServiceLoginRequestDto() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public UserServiceLoginRequestDto setUsername(final String username) {
		this.username = username;

		return this;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public UserServiceLoginRequestDto setPasswordHash(final String passwordHash) {
		this.passwordHash = passwordHash;

		return this;
	}
}