package com.simple_shop.auth_service.model;

import java.util.Objects;

public final class LoginResponse {
	private final boolean credentialsOk;

	private final Long userId;

	private final String errorMessage;

	private final String sessionId;

	private LoginResponse(final LoginResponseBuilder builder) throws IllegalStateException {
		super();

		Objects.requireNonNull(builder);

		builder.validate();

		this.credentialsOk = builder.getCredentialsOk();
		this.userId = builder.getUserId();
		this.errorMessage = builder.getErrorMessage();
		this.sessionId = builder.getSessionId();
	}

	public boolean getCredentialsOk() {
		return this.credentialsOk;
	}

	public Long getUserId() {
		return this.userId;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public static final class LoginResponseBuilder {
		private Boolean credentialsOk;

		private Long userId;

		private String errorMessage;

		private String sessionId;

		public LoginResponseBuilder() {
			super();
		}

		public Boolean getCredentialsOk() {
			return this.credentialsOk;
		}

		public LoginResponseBuilder setCredentialsOk(final Boolean credentialsOk) {
			this.credentialsOk = credentialsOk;

			return this;
		}

		public Long getUserId() {
			return this.userId;
		}

		public LoginResponseBuilder setUserId(final Long userId) {
			this.userId = userId;

			return this;
		}

		public String getErrorMessage() {
			return this.errorMessage;
		}

		public LoginResponseBuilder setErrorMessage(final String errorMessage) {
			this.errorMessage = errorMessage;

			return this;
		}

		public String getSessionId() {
			return this.sessionId;
		}

		public LoginResponseBuilder setSessionId(final String sessionId) {
			this.sessionId = sessionId;

			return this;
		}

		public void validate() throws IllegalStateException {
			if (this.credentialsOk == null) {
				throw new IllegalStateException("Invalid credentialsOk!");
			}

			if (this.userId != null && this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}

			if (this.errorMessage != null && this.errorMessage.isBlank()) {
				throw new IllegalStateException("Invalid error message!");
			}

			if (this.userId != null && (this.sessionId == null || this.sessionId.isBlank())) {
				throw new IllegalStateException("Invalid session ID!");
			}
		}

		/**
		 * @return
		 *
		 * @throws IllegalStateException When the builder doesn't validate properly.
		 */
		public LoginResponse build() throws IllegalStateException {
			return new LoginResponse(this);
		}
	}
}