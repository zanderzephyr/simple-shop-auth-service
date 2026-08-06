package com.simple_shop.auth_service.model;

public final class LoginResponse {
	private final boolean success;

	private final Long userId;

	private final String errorMessage;

	/**
	 * @param  builder
	 *
	 * @throws IllegalStateException When the builder doesn't validate properly.
	 */
	private LoginResponse(final LoginResponseBuilder builder) throws IllegalStateException {
		super();

		builder.validate();

		this.success = builder.getSuccess();
		this.userId = builder.getUserId();
		this.errorMessage = builder.getErrorMessage();
	}

	public boolean getSuccess() {
		return this.success;
	}

	public Long getUserId() {
		return this.userId;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public static final class LoginResponseBuilder {
		private Boolean success;

		private Long userId;

		private String errorMessage;

		public LoginResponseBuilder() {
			super();
		}

		public Boolean getSuccess() {
			return this.success;
		}

		public LoginResponseBuilder setSuccess(final Boolean success) {
			this.success = success;

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

		/**
		 * @throws IllegalStateException When a field is not properly set.
		 */
		public void validate() throws IllegalStateException {
			if (this.success == null) {
				throw new IllegalStateException("Invalid success!");
			}

			if (this.userId != null && this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}

			if (this.errorMessage != null && this.errorMessage.isBlank()) {
				throw new IllegalStateException("Invalid error message!");
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