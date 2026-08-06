package com.simple_shop.auth_service.persistence;

import com.simple_shop.auth_service.model.FailedLogin;
import com.simple_shop.auth_service.model.FailedLogin.FailedLoginBuilder;
import com.simple_shop.auth_service.model.Login;
import com.simple_shop.auth_service.model.Login.LoginBuilder;
import com.simple_shop.auth_service.model.Logout;
import com.simple_shop.auth_service.model.Logout.LogoutBuilder;

public final class PersistenceMapper {
	private PersistenceMapper() {
		throw new UnsupportedOperationException();
	}

	public static Login toDomain(final LoginEntity loginEntity) {
		return new LoginBuilder()//
				.setId(loginEntity.getId()) //
				.setSuccess(loginEntity.getSuccess()) //
				.setTimestamp(loginEntity.getTimestamp()) //
				.setUserId(loginEntity.getUserId()) //
				.setUsername(loginEntity.getUsername())//
				.build();
	}

	public static LoginEntity toEntity(final Login login) {
		final LoginEntity entity = new LoginEntity();

		entity.setId(login.getId());
		entity.setSuccess(login.getSuccess());
		entity.setTimestamp(login.getTimestamp());
		entity.setUserId(login.getUserId());
		entity.setUsername(login.getUsername());

		return entity;
	}

	public static Logout toDomain(final LogoutEntity logoutEntity) {
		return new LogoutBuilder()//
				.setId(logoutEntity.getId()) //
				.setTimestamp(logoutEntity.getTimestamp()) //
				.setUserId(logoutEntity.getUserId()) //
				.build();
	}

	public static LogoutEntity toEntity(final Logout logout) {
		final LogoutEntity entity = new LogoutEntity();

		entity.setId(logout.getId());
		entity.setTimestamp(logout.getTimestamp());
		entity.setUserId(logout.getUserId());

		return entity;
	}

	public static FailedLogin toDomain(final FailedLoginEntity failedLoginEntity) {
		return new FailedLoginBuilder()//
				.setFailedLoginCount((int) failedLoginEntity.getFailedLoginCount()) //
				.setUserId(failedLoginEntity.getUserId()) //
				.build();
	}

	public static FailedLoginEntity toEntity(final FailedLogin failedLogin) {
		final FailedLoginEntity entity = new FailedLoginEntity();

		// FailedLoginBuilder ensures max value is ok
		entity.setFailedLoginCount((byte) failedLogin.getFailedLoginCount());

		entity.setUserId(failedLogin.getUserId());

		return entity;
	}
}
