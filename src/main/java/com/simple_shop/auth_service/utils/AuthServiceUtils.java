package com.simple_shop.auth_service.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class AuthServiceUtils {
	private AuthServiceUtils() {
		throw new UnsupportedOperationException();
	}

	public static LocalDateTime createNow() {
		return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
	}
}