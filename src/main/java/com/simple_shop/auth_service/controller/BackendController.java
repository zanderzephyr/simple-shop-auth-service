package com.simple_shop.auth_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_shop.auth_service.dto.SessionVerificationDto;
import com.simple_shop.auth_service.model.SessionVerification;
import com.simple_shop.auth_service.service.AuthService;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/auth/v1/backend")
@Validated
public final class BackendController {
	private final AuthService authService;

	public BackendController(final AuthService authService) {
		super();

		this.authService = authService;
	}

	@GetMapping("/verify-session/{sessionId}")
	public ResponseEntity<SessionVerificationDto> verifySession(@PathVariable @NotBlank final String sessionId) {
		final SessionVerification sessionVerification = this.authService.verifySession(sessionId);
		final SessionVerificationDto sessionVerificationDto = DtoMapper.toDto(sessionVerification);

		return ResponseEntity.status(HttpStatus.OK).body(sessionVerificationDto);
	}
}