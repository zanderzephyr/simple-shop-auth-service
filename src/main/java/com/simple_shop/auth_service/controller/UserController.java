package com.simple_shop.auth_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_shop.auth_service.dto.LoginRequestDto;
import com.simple_shop.auth_service.dto.LoginResponseDto;
import com.simple_shop.auth_service.exceptions.InvalidRequestException;
import com.simple_shop.auth_service.model.LoginRequest;
import com.simple_shop.auth_service.model.LoginResponse;
import com.simple_shop.auth_service.service.AuthService;

@RestController
@RequestMapping("/api/auth/v1/user")
public final class UserController {
	private final AuthService authService;

	public UserController(final AuthService authService) {
		super();

		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequestDto) {
		if (loginRequestDto == null) {
			throw new InvalidRequestException("No login request!");
		}

		final LoginRequest loginRequest = DtoMapper.toDomain(loginRequestDto);
		final LoginResponse loginResponse = this.authService.login(loginRequest);
		final LoginResponseDto loginResponseDto = DtoMapper.toDto(loginResponse);

		final ResponseEntity<LoginResponseDto> responseEntity;
		if (loginResponse.getSuccess()) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponseDto);
		}
		return responseEntity;
	}

	// TODO What request type?
	public ResponseEntity<Void> logout() {
		// TODO
		return null;
	}
}