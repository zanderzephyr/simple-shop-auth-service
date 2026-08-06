package com.simple_shop.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionVerificationDto {
	private SessionDataDto sessionData;

	public SessionVerificationDto() {
		super();
	}

	public SessionDataDto getSessionData() {
		return this.sessionData;
	}

	public SessionVerificationDto setSessionData(final SessionDataDto sessionData) {
		this.sessionData = sessionData;

		return this;
	}
}