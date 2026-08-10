package com.simple_shop.auth_service.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth/v1/user")
public final class UserController {
	private final AuthService authService;

	public UserController(final AuthService authService) {
		super();

		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(//
			@RequestBody final LoginRequestDto loginRequestDto, final HttpServletResponse response) {

		if (loginRequestDto == null) {
			throw new InvalidRequestException("No login request!");
		}

		final LoginRequest loginRequest = DtoMapper.toDomain(loginRequestDto);
		final LoginResponse loginResponse = this.authService.login(loginRequest);
		final LoginResponseDto loginResponseDto = DtoMapper.toDto(loginResponse);

		final ResponseEntity<LoginResponseDto> responseEntity;
		if (loginResponse.getCredentialsOk()) {
			final ResponseCookie sessionCookie = ResponseCookie.from("session_id", loginResponse.getSessionId())//
					.httpOnly(true)//
					.secure(false)// false for local HTTP dev (true in prod!)
					.path("/")//
					.sameSite("Strict")//
					.build();
			response.addHeader(HttpHeaders.SET_COOKIE, sessionCookie.toString());
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponseDto);
		}
		return responseEntity;
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(//
			@CookieValue(name = "session_id", required = false) final String sessionId, //
			final HttpServletResponse response) {

		/*
		 * sessionId is optional as a session could be expired when a user clicks
		 * logout.
		 */
		if (sessionId != null) {
			/*
			 * TODO Call authService.logout(sessionId) to clean up Redis and add to Logouts
			 * table
			 */
		}

		final ResponseCookie clearingCookie = ResponseCookie.from("session_id", "")//
				.httpOnly(true)//
				.secure(false)//
				.path("/")//
				.maxAge(0)// instructs the browser to delete cookie instantly
				.sameSite("Strict")//
				.build();
		response.addHeader(HttpHeaders.SET_COOKIE, clearingCookie.toString());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}