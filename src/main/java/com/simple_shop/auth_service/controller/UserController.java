package com.simple_shop.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_shop.auth_service.dto.LoginResponseDto;
import com.simple_shop.auth_service.service.AuthService;

@RestController
@RequestMapping("/api/auth/v1/user")
public final class UserController {
	private final AuthService authService;

	public UserController(final AuthService authService) {
		super();

		this.authService = authService;
	}

	// TODO What request type?
	public ResponseEntity<LoginResponseDto> login() {
		// TODO
		return null;
	}

	// TODO What request type?
	public ResponseEntity<Void> logout() {
		// TODO
		return null;
	}
}