package com.simple_shop.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_shop.auth_service.service.AuthService;

@RestController
@RequestMapping("/api/auth/v1/admin")
public final class AdminController {
	private final AuthService authService;

	public AdminController(final AuthService authService) {
		super();

		this.authService = authService;
	}

	// TODO What request type?
	// TODO What response type?
	public ResponseEntity<Void> unlockUser() {
		// TODO
		return null;
	}
}