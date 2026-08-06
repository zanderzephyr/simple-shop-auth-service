package com.simple_shop.auth_service.model;

public final class SessionVerification {
	private final SessionData sessionData;

	/**
	 * @param  builder
	 *
	 * @throws IllegalStateException When the builder doesn't validate properly.
	 */
	private SessionVerification(final SessionDataBuilder builder) throws IllegalStateException {
		super();

		builder.validate();

		this.sessionData = builder.getSessionData();
	}

	public SessionData getSessionData() {
		return this.sessionData;
	}

	public static final class SessionDataBuilder {
		private SessionData sessionData;

		public SessionDataBuilder() {
			super();
		}

		public SessionData getSessionData() {
			return this.sessionData;
		}

		public SessionDataBuilder setSessionData(final SessionData sessionData) {
			this.sessionData = sessionData;

			return this;
		}

		/**
		 * @throws IllegalStateException When a field is not properly set.
		 */
		public void validate() throws IllegalStateException {
			// Nothing so far.
		}

		/**
		 * @return
		 *
		 * @throws IllegalStateException When the builder doesn't validate properly.
		 */
		public SessionVerification build() throws IllegalStateException {
			return new SessionVerification(this);
		}
	}
}