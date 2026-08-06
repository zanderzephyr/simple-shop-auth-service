package com.simple_shop.auth_service.model;

public final class SessionData {
	private final Long userId;

	/**
	 * @param  builder
	 *
	 * @throws IllegalStateException When the builder doesn't validate properly.
	 */
	private SessionData(final SessionDataBuilder builder) throws IllegalStateException {
		super();

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

		/**
		 * @throws IllegalStateException When a field is not properly set.
		 */
		public void validate() throws IllegalStateException {
			if (this.userId != null && this.userId < 0) {
				throw new IllegalStateException("Invalid user ID!");
			}
		}

		/**
		 * @return
		 *
		 * @throws IllegalStateException When the builder doesn't validate properly.
		 */
		public SessionData build() throws IllegalStateException {
			return new SessionData(this);
		}
	}
}