package com.simple_shop.auth_service.service.user_service;

import org.springframework.stereotype.Service;

import com.simple_shop.auth_service.dto.user_service.UserServiceLoginRequestDto;
import com.simple_shop.auth_service.dto.user_service.UserServiceLoginResponseDto;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginRequest;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginResponse;
import com.simple_shop.auth_service.model.user_service.UserServiceUserType;
import com.simple_shop.auth_service.utils.DataTypeUtils;

@Service
public class UserService {
	private final UserServiceBackendHttpClient client;

	public UserService(final UserServiceBackendHttpClient client) {
		super();

		this.client = client;
	}

	// TODO Probably deal with network issues or missing user services etc.
	public UserServiceLoginResponse verifyLoginCredentials(final UserServiceLoginRequest loginRequest) {
		final UserServiceLoginRequestDto loginRequestDto = UserServiceDtoMapper.toDto(loginRequest);
		final UserServiceLoginResponseDto loginResponseDto = this.client.verifyLoginCredentials(loginRequestDto);
		final UserServiceLoginResponse loginResponse = UserServiceDtoMapper.toDomain(loginResponseDto);
		return loginResponse;
	}

	public UserServiceUserType getUserType(final long userId) {
		final String userTypeDto = this.client.getUserType(userId);
		return DataTypeUtils.parseEnum(userTypeDto, UserServiceUserType.class);
	}
}