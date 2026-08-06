package com.simple_shop.auth_service.cache;

import java.util.Objects;

public final class RedisSessionData {
	private final long userId;

	private RedisSessionData(final RedisSessionDataBuilder builder) {
		super();

		Objects.requireNonNull(builder);

		builder.validate();

		this.userId = builder.getUserId();
	}

	public long getUserId() {
		return this.userId;
	}

	public static final class RedisSessionDataBuilder {
		private Long userId;

		public RedisSessionDataBuilder() {
			super();
		}

		public Long getUserId() {
			return this.userId;
		}

		public RedisSessionDataBuilder setUserId(final Long userId) {
			this.userId = userId;

			return this;
		}

		public void validate() throws IllegalStateException {
			if (this.userId == null) {
				throw new IllegalStateException("Invalid user ID!");
			}
		}

		public RedisSessionData build() {
			return new RedisSessionData(this);
		}
	}
}
