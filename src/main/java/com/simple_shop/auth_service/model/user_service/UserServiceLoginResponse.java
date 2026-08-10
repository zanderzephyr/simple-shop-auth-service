package com.simple_shop.auth_service.model.user_service;

import java.util.Objects;

public final class UserServiceLoginResponse {
	private final Long userId;

	private final boolean credentialsOk;

	private UserServiceLoginResponse(final UserServiceLoginResponseBuilder builder) throws IllegalStateException {
		super();

		Objects.requireNonNull(builder);

		builder.validate();

		this.userId = builder.getUserId();
		this.credentialsOk = builder.getCredentialsOk();
	}

	public Long getUserId() {
		return this.userId;
	}

	public boolean getCredentialsOk() {
		return this.credentialsOk;
	}

	public static final class UserServiceLoginResponseBuilder {
		private Long userId;

		private Boolean credentialsOk;

		public UserServiceLoginResponseBuilder() {
			super();
		}

		public Long getUserId() {
			return this.userId;
		}

		public UserServiceLoginResponseBuilder setUserId(final Long userId) {
			this.userId = userId;

			return this;
		}

		public Boolean getCredentialsOk() {
			return this.credentialsOk;
		}

		public UserServiceLoginResponseBuilder setCredentialsOk(final Boolean credentialsOk) {
			this.credentialsOk = credentialsOk;

			return this;
		}

		public void validate() throws IllegalStateException {
			if (this.userId != null && this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}

			if (this.credentialsOk == null) {
				throw new IllegalStateException("Invalid credentialsOk!");
			}
		}

		public UserServiceLoginResponse build() throws IllegalStateException {
			return new UserServiceLoginResponse(this);
		}
	}
}