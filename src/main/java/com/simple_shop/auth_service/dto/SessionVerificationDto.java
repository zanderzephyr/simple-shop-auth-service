package com.simple_shop.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionVerificationDto {
	private SessionDataDto sessionData;

	private Boolean success;

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

	public Boolean getSuccess() {
		return this.success;
	}

	public SessionVerificationDto setSuccess(final Boolean success) {
		this.success = success;

		return this;
	}
}