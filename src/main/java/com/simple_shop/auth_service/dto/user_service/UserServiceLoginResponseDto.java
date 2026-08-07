package com.simple_shop.auth_service.dto.user_service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simple_shop.auth_service.dto.formatter.JsonLongFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserServiceLoginResponseDto {
	@JsonLongFormatter
	private Long userId;

	private Boolean success;

	public UserServiceLoginResponseDto() {
		super();
	}

	public Long getUserId() {
		return this.userId;
	}

	public UserServiceLoginResponseDto setUserId(final Long userId) {
		this.userId = userId;

		return this;
	}

	public Boolean getSuccess() {
		return this.success;
	}

	public UserServiceLoginResponseDto setSuccess(final Boolean success) {
		this.success = success;

		return this;
	}
}