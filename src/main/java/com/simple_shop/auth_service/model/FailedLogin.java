package com.simple_shop.auth_service.model;

import java.util.Objects;

public final class FailedLogin {
	private final long userId;

	private final int failedLoginCount;

	private FailedLogin(final FailedLoginBuilder builder) throws IllegalStateException {
		super();

		Objects.requireNonNull(builder);

		builder.validate();

		this.userId = builder.getUserId();
		this.failedLoginCount = builder.getFailedLoginCount();
	}

	public long getUserId() {
		return this.userId;
	}

	public int getFailedLoginCount() {
		return this.failedLoginCount;
	}

	public static final class FailedLoginBuilder {
		private Long userId;

		private Integer failedLoginCount;

		public FailedLoginBuilder() {
			super();
		}

		public Long getUserId() {
			return this.userId;
		}

		public FailedLoginBuilder setUserId(final Long userId) {
			this.userId = userId;

			return this;
		}

		public Integer getFailedLoginCount() {
			return this.failedLoginCount;
		}

		public FailedLoginBuilder setFailedLoginCount(final Integer failedLoginCount) {
			this.failedLoginCount = failedLoginCount;

			return this;
		}

		public void validate() throws IllegalStateException {
			if (this.userId == null || this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}

			if (this.failedLoginCount == null || this.failedLoginCount < 0 || this.failedLoginCount > Byte.MAX_VALUE) {
				throw new IllegalStateException("Invalid failed login count!");
			}
		}

		public FailedLogin build() throws IllegalStateException {
			return new FailedLogin(this);
		}

		public static FailedLoginBuilder initFrom(final FailedLogin failedLogin) {
			return new FailedLoginBuilder()//
					.setFailedLoginCount(failedLogin.getFailedLoginCount())//
					.setUserId(failedLogin.getUserId())//
			;
		}
	}
}