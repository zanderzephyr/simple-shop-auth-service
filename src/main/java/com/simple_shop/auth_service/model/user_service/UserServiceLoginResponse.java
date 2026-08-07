package com.simple_shop.auth_service.model.user_service;

import java.util.Objects;

public final class UserServiceLoginResponse {
	private final Long userId;

	private final boolean success;

	private UserServiceLoginResponse(final UserServiceLoginResponseBuilder builder) throws IllegalStateException {
		super();

		Objects.requireNonNull(builder);

		builder.validate();

		this.userId = builder.getUserId();
		this.success = builder.getSuccess();
	}

	public Long getUserId() {
		return this.userId;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public static final class UserServiceLoginResponseBuilder {
		private Long userId;

		private Boolean success;

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

		public Boolean getSuccess() {
			return this.success;
		}

		public UserServiceLoginResponseBuilder setSuccess(final Boolean success) {
			this.success = success;

			return this;
		}

		public void validate() throws IllegalStateException {
			if (this.userId != null && this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}

			if (this.success == null) {
				throw new IllegalStateException("Invalid success!");
			}
		}

		public UserServiceLoginResponse build() throws IllegalStateException {
			return new UserServiceLoginResponse(this);
		}
	}
}