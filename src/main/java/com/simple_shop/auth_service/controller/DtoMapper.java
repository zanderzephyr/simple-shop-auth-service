package com.simple_shop.auth_service.controller;

import com.simple_shop.auth_service.dto.LoginRequestDto;
import com.simple_shop.auth_service.dto.LoginResponseDto;
import com.simple_shop.auth_service.dto.SessionDataDto;
import com.simple_shop.auth_service.dto.SessionVerificationDto;
import com.simple_shop.auth_service.exceptions.InvalidRequestException;
import com.simple_shop.auth_service.model.ClientType;
import com.simple_shop.auth_service.model.LoginRequest;
import com.simple_shop.auth_service.model.LoginRequest.LoginRequestBuilder;
import com.simple_shop.auth_service.model.LoginResponse;
import com.simple_shop.auth_service.model.SessionData;
import com.simple_shop.auth_service.model.SessionVerification;
import com.simple_shop.auth_service.utils.DataTypeUtils;

/*
 * This is the location to set the precision of decimals, should we ever have
 * them and need precision. Do not solve this using the formatters.
 */
final class DtoMapper {
	private DtoMapper() {
		throw new UnsupportedOperationException();
	}

	public static LoginRequest toDomain(final LoginRequestDto loginRequestDto) {
		try {
			return new LoginRequestBuilder()//
					.setUsername(loginRequestDto.getUsername())//
					.setPasswordHash(loginRequestDto.getPasswordHash())//
					.setClientType(DataTypeUtils.parseEnum(loginRequestDto.getClientType(), ClientType.class))//
					.build();
		} catch (final Exception e) {
			throw new InvalidRequestException(e);
		}
	}

	public static LoginResponseDto toDto(final LoginResponse loginResponse) {
		final LoginResponseDto loginResponseDto = new LoginResponseDto();

		loginResponseDto.setErrorMessage(loginResponse.getErrorMessage());
		loginResponseDto.setSuccess(loginResponse.getSuccess());
		loginResponseDto.setUserId(loginResponse.getUserId());

		// Not setting the session ID here. This field is just for internal use.

		return loginResponseDto;
	}

	public static SessionVerificationDto toDto(final SessionVerification sessionVerification) {
		final SessionVerificationDto sessionVerificationDto = new SessionVerificationDto();

		sessionVerificationDto.setSessionData(DtoMapper.toDto(sessionVerification.getSessionData()));

		return sessionVerificationDto;
	}

	private static SessionDataDto toDto(final SessionData sessionData) {
		final SessionDataDto sessionDataDto = new SessionDataDto();

		sessionDataDto.setUserId(sessionData.getUserId());

		return sessionDataDto;
	}
}
