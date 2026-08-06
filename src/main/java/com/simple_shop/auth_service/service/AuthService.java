package com.simple_shop.auth_service.service;

import org.springframework.stereotype.Service;

import com.simple_shop.auth_service.cache.RedisSessionData;
import com.simple_shop.auth_service.cache.RedisSessionService;
import com.simple_shop.auth_service.model.SessionData;
import com.simple_shop.auth_service.model.SessionData.SessionDataBuilder;
import com.simple_shop.auth_service.model.SessionVerification;
import com.simple_shop.auth_service.model.SessionVerification.SessionVerificationBuilder;
import com.simple_shop.auth_service.persistence.AuthTransactionHandler;

@Service
public final class AuthService {
	private final AuthTransactionHandler authTransactionHandler;

	private final RedisSessionService redisSessionService;

	public AuthService(//
			final AuthTransactionHandler authTransactionHandler, final RedisSessionService redisSessionService) {

		super();

		this.authTransactionHandler = authTransactionHandler;
		this.redisSessionService = redisSessionService;
	}

	public SessionVerification verifySession(final String sessionId) {
		final RedisSessionData redisSessionData = this.redisSessionService.getSession(sessionId);
		final SessionData sessionData = new SessionDataBuilder()//
				.setUserId(redisSessionData.getUserId())//
				.build();
		return new SessionVerificationBuilder()//
				.setSessionData(sessionData)//
				.build();
	}
}
