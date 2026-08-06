package com.simple_shop.auth_service.model;

public final class SessionVerification {
	private final SessionData sessionData;

	/**
	 * @param  builder
	 *
	 * @throws IllegalStateException When the builder doesn't validate properly.
	 */
	private SessionVerification(final SessionVerificationBuilder builder) throws IllegalStateException {
		super();

		builder.validate();

		this.sessionData = builder.getSessionData();
	}

	public SessionData getSessionData() {
		return this.sessionData;
	}

	public static final class SessionVerificationBuilder {
		private SessionData sessionData;

		public SessionVerificationBuilder() {
			super();
		}

		public SessionData getSessionData() {
			return this.sessionData;
		}

		public SessionVerificationBuilder setSessionData(final SessionData sessionData) {
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