package com.simple_shop.auth_service.service;

import org.springframework.stereotype.Service;

import com.simple_shop.auth_service.model.FailedLogin;
import com.simple_shop.auth_service.model.FailedLogin.FailedLoginBuilder;
import com.simple_shop.auth_service.model.LoginRequest;
import com.simple_shop.auth_service.model.LoginResponse;
import com.simple_shop.auth_service.model.LoginResponse.LoginResponseBuilder;
import com.simple_shop.auth_service.model.SessionData;
import com.simple_shop.auth_service.model.SessionData.SessionDataBuilder;
import com.simple_shop.auth_service.model.SessionVerification;
import com.simple_shop.auth_service.model.SessionVerification.SessionVerificationBuilder;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginRequest;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginRequest.UserServiceLoginRequestBuilder;
import com.simple_shop.auth_service.model.user_service.UserServiceLoginResponse;
import com.simple_shop.auth_service.persistence.AuthTransactionHandler;
import com.simple_shop.auth_service.service.redis.RedisSessionData;
import com.simple_shop.auth_service.service.redis.RedisSessionData.RedisSessionDataBuilder;
import com.simple_shop.auth_service.service.redis.RedisSessionService;
import com.simple_shop.auth_service.service.user_service.UserService;

@Service
public final class AuthService {
	private static final int USER_LOCKED_LOGIN_COUNT = 3;

	private final AuthTransactionHandler authTransactionHandler;

	private final RedisSessionService redisSessionService;

	private final UserService userService;

	public AuthService(//
			final AuthTransactionHandler authTransactionHandler, //
			final RedisSessionService redisSessionService, //
			final UserService userService) {

		super();

		this.authTransactionHandler = authTransactionHandler;
		this.redisSessionService = redisSessionService;
		this.userService = userService;
	}

	public SessionVerification verifySession(final String sessionId) {
		final RedisSessionData redisSessionData = this.redisSessionService.getSession(sessionId);

		final SessionVerificationBuilder builder = new SessionVerificationBuilder();
		if (redisSessionData != null) {
			final SessionData sessionData = new SessionDataBuilder()//
					.setUserId(redisSessionData.getUserId())//
					.build();
			builder.setSessionData(sessionData);
		}

		return builder.build();
	}

	public LoginResponse login(final LoginRequest loginRequest) {
		final UserServiceLoginRequest userServiceLoginRequest = new UserServiceLoginRequestBuilder()//
				.setPasswordHash(loginRequest.getPasswordHash())//
				.setUsername(loginRequest.getUsername())//
				.build();

		final UserServiceLoginResponse userServiceLoginResponse = this.userService
				.verifyLoginCredentials(userServiceLoginRequest);

		final Long userId = userServiceLoginResponse.getUserId();
		final boolean credentialsOk = userServiceLoginResponse.getCredentialsOk();
		final boolean userLocked = this.isUserLocked(userId);

		if (userId != null && !userLocked) {
			if (credentialsOk) {
				this.updateFailedLoginsForLoginSucceeded(userId, loginRequest);

				final RedisSessionData redisSessionData = new RedisSessionDataBuilder()//
						.setUserId(userId)//
						.build();
				final String sessionId = this.redisSessionService.createSession(redisSessionData);

				return new LoginResponseBuilder()//
						.setCredentialsOk(Boolean.TRUE)//
						.setUserId(userId)//
						.setSessionId(sessionId)//
						.build();
			}

			/*
			 * We only update the failed counter when a user is not already locked, to
			 * prevent an unnecessary writing operation in the DB.
			 */
			this.updateFailedLoginsForLoginFailed(userId, loginRequest);
		}
		return new LoginResponseBuilder()//
				.setErrorMessage("Invalid credentials")//
				.setCredentialsOk(Boolean.FALSE)//
				.build();
	}

	private void updateFailedLoginsForLoginSucceeded(final long userId, final LoginRequest loginRequest) {
		final FailedLogin currentFailedLogin = this.authTransactionHandler.getFailedLogins(userId);

		if (currentFailedLogin != null) {
			final FailedLoginBuilder failedLoginBuilder = FailedLoginBuilder.initFrom(currentFailedLogin)
					.setFailedLoginCount(0);
			this.authTransactionHandler.saveFailedLogin(failedLoginBuilder.build());
		}
	}

	private void updateFailedLoginsForLoginFailed(final long userId, final LoginRequest loginRequest) {
		final FailedLogin currentFailedLogin = this.authTransactionHandler.getFailedLogins(userId);

		final FailedLoginBuilder failedLoginBuilder;
		if (currentFailedLogin == null) {
			failedLoginBuilder = new FailedLoginBuilder()//
					.setUserId(userId)//
					.setFailedLoginCount(1);
		} else {
			failedLoginBuilder = FailedLoginBuilder.initFrom(currentFailedLogin);
			failedLoginBuilder.setFailedLoginCount(failedLoginBuilder.getFailedLoginCount() + 1);
		}

		this.authTransactionHandler.saveFailedLogin(failedLoginBuilder.build());
	}

	private boolean isUserLocked(final Long userId) {
		if (userId != null) {
			final FailedLogin failedLogin = this.authTransactionHandler.getFailedLogins(userId);
			return failedLogin != null && failedLogin.getFailedLoginCount() >= AuthService.USER_LOCKED_LOGIN_COUNT;
		}
		return Boolean.FALSE;
	}

	public void logout(final String sessionId) {
		this.redisSessionService.deleteSession(sessionId);
	}
}
