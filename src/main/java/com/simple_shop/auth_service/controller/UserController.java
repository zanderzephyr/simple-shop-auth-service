package com.simple_shop.auth_service.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_shop.auth_service.dto.CurrentUserDto;
import com.simple_shop.auth_service.dto.LoginRequestDto;
import com.simple_shop.auth_service.dto.LoginResponseDto;
import com.simple_shop.auth_service.exceptions.InvalidMessageException;
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
			throw new InvalidMessageException("No login request!");
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
			this.authService.logout(sessionId);
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

	/*
	 * This endpoint is a bit tricky. Whenever the frontend initializes, it needs to
	 * know if a session already exists. I calls this endpoint. The gateway forwards
	 * the request for both cases - session exists / session doesn't exist - (it
	 * decides based on the HttpOnly session cookie). All, the auth service has to
	 * do now, is mirror the user ID, the gateway already determined, back to the
	 * frontend. With that user ID, the frontend can achieve the rest. This endpoint
	 * is so simple, we do not forward to the service as a consequence.
	 */
	@GetMapping("/me")
	public ResponseEntity<CurrentUserDto> getCurrentUser(//
			@RequestHeader(name = "X-User-Id", required = false) final Long userId) {

		final CurrentUserDto currentUserDto = new CurrentUserDto();
		currentUserDto.setUserId(userId);

		return ResponseEntity.status(HttpStatus.OK).body(currentUserDto);
	}
}