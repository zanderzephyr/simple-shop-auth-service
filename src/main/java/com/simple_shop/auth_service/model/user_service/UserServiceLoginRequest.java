package com.simple_shop.auth_service.model.user_service;

import java.util.Objects;

public final class UserServiceLoginRequest {
	private final String username;

	private final String passwordHash;

	private UserServiceLoginRequest(final UserServiceLoginRequestBuilder builder) throws IllegalStateException {
		super();

		Objects.requireNonNull(builder);

		builder.validate();

		this.username = builder.getUsername();
		this.passwordHash = builder.getPasswordHash();
	}

	public String getUsername() {
		return this.username;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public static final class UserServiceLoginRequestBuilder {
		private String username;

		private String passwordHash;

		public UserServiceLoginRequestBuilder() {
			super();
		}

		public String getUsername() {
			return this.username;
		}

		public UserServiceLoginRequestBuilder setUsername(final String username) {
			this.username = username;

			return this;
		}

		public String getPasswordHash() {
			return this.passwordHash;
		}

		public UserServiceLoginRequestBuilder setPasswordHash(final String passwordHash) {
			this.passwordHash = passwordHash;

			return this;
		}

		public void validate() throws IllegalStateException {
			// No need to validate the db length requirements here
			if (this.username == null || this.username.isBlank()) {
				throw new IllegalStateException("Invalid username!");
			}

			// No need to validate the db length requirements here
			if (this.passwordHash == null || this.passwordHash.isBlank()) {
				throw new IllegalStateException("Invalid password hash!");
			}
		}

		public UserServiceLoginRequest build() throws IllegalStateException {
			return new UserServiceLoginRequest(this);
		}
	}
}