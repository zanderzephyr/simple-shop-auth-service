package com.simple_shop.auth_service.persistence;

import com.simple_shop.auth_service.model.FailedLogin;
import com.simple_shop.auth_service.model.FailedLogin.FailedLoginBuilder;

public final class PersistenceMapper {
	private PersistenceMapper() {
		throw new UnsupportedOperationException();
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
