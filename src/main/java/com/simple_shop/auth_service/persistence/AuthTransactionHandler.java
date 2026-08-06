package com.simple_shop.auth_service.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.simple_shop.auth_service.model.FailedLogin;
import com.simple_shop.auth_service.model.Login;
import com.simple_shop.auth_service.model.Logout;
import com.simple_shop.auth_service.repository.FailedLoginRepository;
import com.simple_shop.auth_service.repository.LoginRepository;
import com.simple_shop.auth_service.repository.LogoutRepository;

@Component
public class AuthTransactionHandler {
	private final LoginRepository loginRepository;

	private final LogoutRepository logoutRepository;

	private final FailedLoginRepository failedLoginRepository;

	public AuthTransactionHandler(//
			final LoginRepository loginRepository, //
			final LogoutRepository logoutRepository, //
			final FailedLoginRepository failedLoginRepository) {

		super();

		this.loginRepository = loginRepository;
		this.logoutRepository = logoutRepository;
		this.failedLoginRepository = failedLoginRepository;
	}

	@Transactional
	public void saveLogin(final Login login) {
		final LoginEntity loginEntity = PersistenceMapper.toEntity(login);
		this.loginRepository.save(loginEntity);
	}

	@Transactional
	public void saveLogout(final Logout logout) {
		final LogoutEntity logoutEntity = PersistenceMapper.toEntity(logout);
		this.logoutRepository.save(logoutEntity);
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
		final FailedLoginEntity failedLoginEntity = this.failedLoginRepository.findById(userId).orElseGet(null);
		return PersistenceMapper.toDomain(failedLoginEntity);
	}
}
