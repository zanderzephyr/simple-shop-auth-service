package com.simple_shop.auth_service.service.user_service;

import com.simple_shop.auth_service.dto.user_service.UserServiceLoginRequestDto;
import com.simple_shop.auth_service.dto.user_service.UserServiceLoginResponseDto;
import com.simple_shop.auth_service.exceptions.InvalidMessageException;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginRequest;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginResponse;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginResponse.UserServiceLoginResponseBuilder;

final class UserServiceDtoMapper {
	private UserServiceDtoMapper() {
		throw new UnsupportedOperationException();
	}

	public static UserServiceLoginRequestDto toDto(final UserServiceLoginRequest loginRequest) {
		final UserServiceLoginRequestDto loginRequestDto = new UserServiceLoginRequestDto();

		loginRequestDto.setUsername(loginRequest.getUsername());
		loginRequestDto.setPasswordHash(loginRequest.getPasswordHash());

		return loginRequestDto;
	}

	public static UserServiceLoginResponse toDomain(final UserServiceLoginResponseDto loginResponseDto) {
		try {
			return new UserServiceLoginResponseBuilder()//
					.setCredentialsOk(loginResponseDto.getCredentialsOk())//
					.setUserId(loginResponseDto.getUserId())//
					.build();
		} catch (final Exception e) {
			throw new InvalidMessageException(e);
		}
	}
}
