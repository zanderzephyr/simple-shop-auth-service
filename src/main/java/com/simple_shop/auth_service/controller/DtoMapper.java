package com.simple_shop.auth_service.controller;

import com.simple_shop.auth_service.dto.LoginRequestDto;
import com.simple_shop.auth_service.exceptions.LoginRequestValidationException;
import com.simple_shop.auth_service.model.ClientType;
import com.simple_shop.auth_service.model.LoginRequest;
import com.simple_shop.auth_service.model.LoginRequest.LoginRequestBuilder;
import com.simple_shop.auth_service.utils.DataTypeUtils;

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

	/*
	 * This is the location to set the precision of decimals, should we ever have
	 * them and need precision. Do not solve this using the formatters.
	 */
	public static LoginRequestDto toDto(final LoginRequest loginRequest) {
		final LoginRequestDto loginRequestDto = new LoginRequestDto();

		loginRequestDto.setUsername(loginRequest.getUsername());
		loginRequestDto.setPasswordHash(loginRequest.getPasswordHash());
		loginRequestDto.setClientType(loginRequest.getClientType().name());

		return loginRequestDto;
	}
}
