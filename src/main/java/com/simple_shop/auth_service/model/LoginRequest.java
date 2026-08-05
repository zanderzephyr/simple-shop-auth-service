package com.simple_shop.auth_service.model;

import com.simple_shop.auth_service.exceptions.LoginRequestValidationException;

public final class LoginRequest {
	private final String username;

	private final String passwordHash;

	private final ClientType clientType;

	/**
	 * @param  builder
	 *
	 * @throws LoginRequestValidationException When the builder doesn't validate
	 *                                         properly.
	 */
	private LoginRequest(final LoginRequestBuilder builder) throws LoginRequestValidationException {
		super();

		builder.validate();

		this.username = builder.getUsername();
		this.passwordHash = builder.getPasswordHash();
		this.clientType = builder.getClientType();
	}

	public String getUsername() {
		return this.username;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public ClientType getClientType() {
		return this.clientType;
	}

	public static final class LoginRequestBuilder {
		private String username;

		private String passwordHash;

		private ClientType clientType;

		public LoginRequestBuilder() {
			super();
		}

		public String getUsername() {
			return this.username;
		}

		public LoginRequestBuilder setUsername(final String username) {
			this.username = username;

			return this;
		}

		public String getPasswordHash() {
			return this.passwordHash;
		}

		public LoginRequestBuilder setPasswordHash(final String passwordHash) {
			this.passwordHash = passwordHash;

			return this;
		}

		public ClientType getClientType() {
			return this.clientType;
		}

		public LoginRequestBuilder setClientType(final ClientType clientType) {
			this.clientType = clientType;

			return this;
		}

		/**
		 * @throws LoginRequestValidationException When a field is not properly set.
		 */
		public void validate() throws LoginRequestValidationException {
			if (this.username == null || this.username.isBlank() || this.username.length() > 30) {
				throw new LoginRequestValidationException("Invalid username!");
			}

			if (this.passwordHash == null || this.passwordHash.isBlank()) {
				throw new LoginRequestValidationException("Invalid password hash!");
			}

			if (this.clientType == null) {
				throw new LoginRequestValidationException("Invalid client type!");
			}
		}

		/**
		 * @return
		 *
		 * @throws LoginRequestValidationException When the builder doesn't validate
		 *                                         properly.
		 */
		public LoginRequest build() throws LoginRequestValidationException {
			return new LoginRequest(this);
		}
	}
}