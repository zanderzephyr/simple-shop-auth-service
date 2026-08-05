package com.simple_shop.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_shop.auth_service.dto.SessionVerificationDto;
import com.simple_shop.auth_service.service.AuthService;

@RestController
@RequestMapping("/api/auth/v1/backend")
public final class BackendController {
	private final AuthService authService;

	public BackendController(final AuthService authService) {
		super();

		this.authService = authService;
	}

	// TODO What request type?
	public ResponseEntity<SessionVerificationDto> verifySession() {
		// TODO
		return null;
	}
}