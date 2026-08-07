package com.simple_shop.auth_service.model;

import java.util.Objects;

public final class SessionVerification {
	private final SessionData sessionData;

	private SessionVerification(final SessionVerificationBuilder builder) throws IllegalStateException {
		super();

		Objects.requireNonNull(builder);

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

		public void validate() throws IllegalStateException {
			// Nothing so far.
		}

		public SessionVerification build() throws IllegalStateException {
			return new SessionVerification(this);
		}
	}
}