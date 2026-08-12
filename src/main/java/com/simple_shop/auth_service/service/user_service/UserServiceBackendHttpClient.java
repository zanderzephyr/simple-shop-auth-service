package com.simple_shop.auth_service.service.user_service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.simple_shop.auth_service.dto.user_service.UserServiceLoginRequestDto;
import com.simple_shop.auth_service.dto.user_service.UserServiceLoginResponseDto;

@HttpExchange("/api/users/v1")
interface UserServiceBackendHttpClient {
	@PostExchange("/backend/verify-credentials")
	UserServiceLoginResponseDto verifyLoginCredentials(@RequestBody UserServiceLoginRequestDto request);

	@GetExchange("/regular/get-user-type/{userId}")
	String getUserType(@PathVariable Long userId);
}
