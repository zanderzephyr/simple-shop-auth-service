package com.simple_shop.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simple_shop.auth_service.dto.formatter.JsonLongFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentUserDto {
	@JsonLongFormatter
	private Long userId;

	public CurrentUserDto() {
		super();
	}

	public Long getUserId() {
		return this.userId;
	}

	public CurrentUserDto setUserId(final Long userId) {
		this.userId = userId;

		return this;
	}
}