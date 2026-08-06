package com.simple_shop.auth_service.model;

import java.time.LocalDateTime;

public final class Login {
	private final Long id;

	private final LocalDateTime timestamp;

	private final String username;

	private final Long userId;

	private final boolean success;

	/**
	 * @param  builder
	 *
	 * @throws IllegalStateException When the builder doesn't validate properly.
	 */
	private Login(final LoginBuilder builder) throws IllegalStateException {
		super();

		builder.validate();

		this.id = builder.getId();
		this.timestamp = builder.getTimestamp();
		this.username = builder.getUsername();
		this.userId = builder.getUserId();
		this.success = builder.getSuccess();
	}

	public Long getId() {
		return this.id;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public String getUsername() {
		return this.username;
	}

	public Long getUserId() {
		return this.userId;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public static final class LoginBuilder {
		private Long id;

		private LocalDateTime timestamp;

		private String username;

		private Long userId;

		private Boolean success;

		public LoginBuilder() {
			super();
		}

		public Long getId() {
			return this.id;
		}

		public LoginBuilder setId(final Long id) {
			this.id = id;

			return this;
		}

		public LocalDateTime getTimestamp() {
			return this.timestamp;
		}

		public LoginBuilder setTimestamp(final LocalDateTime timestamp) {
			this.timestamp = timestamp;

			return this;
		}

		public String getUsername() {
			return this.username;
		}

		public LoginBuilder setUsername(final String username) {
			this.username = username;

			return this;
		}

		public Long getUserId() {
			return this.userId;
		}

		public LoginBuilder setUserId(final Long userId) {
			this.userId = userId;

			return this;
		}

		public Boolean getSuccess() {
			return this.success;
		}

		public LoginBuilder setSuccess(final Boolean success) {
			this.success = success;

			return this;
		}

		/**
		 * @throws IllegalStateException When a field is not properly set.
		 */
		public void validate() throws IllegalStateException {
			if (this.id < 0) {
				throw new IllegalStateException("Invalid ID!");
			}

			if (this.timestamp == null) {
				throw new IllegalStateException("Invalid timestamp!");
			}

			if (this.username != null && (this.username.isBlank() || this.username.length() > 30)) {
				throw new IllegalStateException("Invalid username!");
			}

			if (this.userId != null && this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}

			if (this.success == null) {
				throw new IllegalStateException("Invalid success!");
			}
		}

		/**
		 * @return
		 *
		 * @throws IllegalStateException When the builder doesn't validate properly.
		 */
		public Login build() throws IllegalStateException {
			return new Login(this);
		}
	}
}