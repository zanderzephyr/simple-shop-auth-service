package com.simple_shop.auth_service.model;

import java.util.Objects;

public final class SessionData {
	private final Long userId;

	private SessionData(final SessionDataBuilder builder) throws IllegalStateException {
		super();

		Objects.requireNonNull(builder);

		builder.validate();

		this.userId = builder.getUserId();
	}

	public Long getUserId() {
		return this.userId;
	}

	public static final class SessionDataBuilder {
		private Long userId;

		public SessionDataBuilder() {
			super();
		}

		public Long getUserId() {
			return this.userId;
		}

		public SessionDataBuilder setUserId(final Long userId) {
			this.userId = userId;

			return this;
		}

		public void validate() throws IllegalStateException {
			if (this.userId != null && this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}
		}

		public SessionData build() throws IllegalStateException {
			return new SessionData(this);
		}
	}
}