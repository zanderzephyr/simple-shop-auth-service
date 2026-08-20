package com.simple_shop.auth_service.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.simple_shop.auth_service.model.FailedLogin;
import com.simple_shop.auth_service.repository.FailedLoginRepository;

@Component
public class AuthTransactionHandler {
	private final FailedLoginRepository failedLoginRepository;

	public AuthTransactionHandler(final FailedLoginRepository failedLoginRepository) {
		super();

		this.failedLoginRepository = failedLoginRepository;
	}

	@Transactional
	public void saveFailedLogin(final FailedLogin failedLogin) {
		final FailedLoginEntity failedLoginEntity = PersistenceMapper.toEntity(failedLogin);
		this.failedLoginRepository.save(failedLoginEntity);
	}

	/**
	 * @param  userId
	 *
	 * @return        The found failed logins or null if nothing was found.
	 */
	@Transactional(readOnly = true)
	public FailedLogin getFailedLogins(final long userId) {
		final FailedLoginEntity failedLoginEntity = this.failedLoginRepository.findById(userId).orElseGet(() -> null);
		return failedLoginEntity == null ? null : PersistenceMapper.toDomain(failedLoginEntity);
	}
}
