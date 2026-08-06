package com.simple_shop.auth_service.controller;

import com.simple_shop.auth_service.dto.LoginRequestDto;
import com.simple_shop.auth_service.dto.LoginResponseDto;
import com.simple_shop.auth_service.dto.SessionDataDto;
import com.simple_shop.auth_service.dto.SessionVerificationDto;
import com.simple_shop.auth_service.exceptions.LoginRequestValidationException;
import com.simple_shop.auth_service.model.ClientType;
import com.simple_shop.auth_service.model.LoginRequest;
import com.simple_shop.auth_service.model.LoginRequest.LoginRequestBuilder;
import com.simple_shop.auth_service.model.LoginResponse;
import com.simple_shop.auth_service.model.LoginResponse.LoginResponseBuilder;
import com.simple_shop.auth_service.model.SessionData;
import com.simple_shop.auth_service.model.SessionVerification;
import com.simple_shop.auth_service.utils.DataTypeUtils;

/*
 * This is the location to set the precision of decimals, should we ever have
 * them and need precision. Do not solve this using the formatters.
 */
public final class DtoMapper {
	private DtoMapper() {
		throw new UnsupportedOperationException();
	}

	public static LoginRequest toDomain(final LoginRequestDto loginRequestDto) {
		return new LoginRequestBuilder()//
				.setUsername(loginRequestDto.getUsername())//
				.setPasswordHash(loginRequestDto.getPasswordHash())//
				.setClientType(DtoMapper.parseClientType(loginRequestDto.getClientType()))//
				.build();
	}

	private static ClientType parseClientType(final String clientType) {
		try {
			return DataTypeUtils.parseEnum(clientType, ClientType.class);
		} catch (final IllegalArgumentException e) {
			throw new LoginRequestValidationException("Unknown client type: " + clientType);
		}
	}

	public static LoginRequestDto toDto(final LoginRequest loginRequest) {
		final LoginRequestDto loginRequestDto = new LoginRequestDto();

		loginRequestDto.setUsername(loginRequest.getUsername());
		loginRequestDto.setPasswordHash(loginRequest.getPasswordHash());
		loginRequestDto.setClientType(loginRequest.getClientType().name());

		return loginRequestDto;
	}

	public static LoginResponse toDomain(final LoginResponseDto loginResponseDto) {
		return new LoginResponseBuilder()//
				.setErrorMessage(loginResponseDto.getErrorMessage())//
				.setSuccess(loginResponseDto.getSuccess())//
				.setUserId(loginResponseDto.getUserId())//
				.build();
	}

	public static LoginResponseDto toDto(final LoginResponse loginResponse) {
		final LoginResponseDto loginResponseDto = new LoginResponseDto();

		loginResponseDto.setErrorMessage(loginResponse.getErrorMessage());
		loginResponseDto.setSuccess(loginResponse.getSuccess());
		loginResponseDto.setUserId(loginResponse.getUserId());

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
