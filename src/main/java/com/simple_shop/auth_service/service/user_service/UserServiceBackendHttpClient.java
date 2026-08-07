package com.simple_shop.auth_service.service.user_service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.simple_shop.auth_service.dto.user_service.UserServiceLoginRequestDto;
import com.simple_shop.auth_service.dto.user_service.UserServiceLoginResponseDto;

@HttpExchange("/api/users/v1/backend")
interface UserServiceBackendHttpClient {
	@PostExchange("/verify-credentials")
	UserServiceLoginResponseDto verifyLoginCredentials(@RequestBody UserServiceLoginRequestDto request);
}
