package com.simple_shop.auth_service.model;

import java.time.LocalDateTime;

public final class Logout {
	private final Long id;

	private final LocalDateTime timestamp;

	private final Long userId;

	/**
	 * @param  builder
	 *
	 * @throws IllegalStateException When the builder doesn't validate properly.
	 */
	private Logout(final LogoutBuilder builder) throws IllegalStateException {
		super();

		builder.validate();

		this.id = builder.getId();
		this.timestamp = builder.getTimestamp();
		this.userId = builder.getUserId();
	}

	public Long getId() {
		return this.id;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public Long getUserId() {
		return this.userId;
	}

	public static final class LogoutBuilder {
		private Long id;

		private LocalDateTime timestamp;

		private Long userId;

		public LogoutBuilder() {
			super();
		}

		public Long getId() {
			return this.id;
		}

		public LogoutBuilder setId(final Long id) {
			this.id = id;

			return this;
		}

		public LocalDateTime getTimestamp() {
			return this.timestamp;
		}

		public LogoutBuilder setTimestamp(final LocalDateTime timestamp) {
			this.timestamp = timestamp;

			return this;
		}

		public Long getUserId() {
			return this.userId;
		}

		public LogoutBuilder setUserId(final Long userId) {
			this.userId = userId;

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

			if (this.userId == null || this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}
		}

		/**
		 * @return
		 *
		 * @throws IllegalStateException When the builder doesn't validate properly.
		 */
		public Logout build() throws IllegalStateException {
			return new Logout(this);
		}
	}
}