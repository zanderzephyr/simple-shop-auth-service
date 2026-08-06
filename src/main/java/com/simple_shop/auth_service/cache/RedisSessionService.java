package com.simple_shop.auth_service.cache;

import java.util.UUID;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/*
 * If the redis service is not available somehow, there are going to be
 * exceptions thrown by Spring. We don't care for those right now and let them
 * move requests into HTTP 500 state.
 */
@Service
public class RedisSessionService {
	private static final int MAX_CREATION_BOUNDARY = 100;

	private final StringRedisTemplate redisTemplate;

	public RedisSessionService(final StringRedisTemplate redisTemplate) {
		super();

		this.redisTemplate = redisTemplate;
	}

	/**
	 * Creates a session in the Redis cache and returns the session ID. (The
	 * returned session ID is the key in Redis and the sessionValue parameter the
	 * value in Redis.)
	 *
	 * @param  sessionValue
	 *
	 * @return
	 */
	public String createSession(final String sessionValue) {
		boolean isSaved = false;
		int iteration = 0;
		String sessionId = null;

		while (!isSaved) {
			iteration++;
			if (iteration > RedisSessionService.MAX_CREATION_BOUNDARY) {
				throw new IllegalStateException("Redis session highly saturated! Failed to create session!");
			}

			sessionId = UUID.randomUUID().toString();
			isSaved = this.createSession(sessionId, sessionValue);
		}

		return sessionId;
	}

	private boolean createSession(final String key, final String value) {
		final Boolean isSaved = this.redisTemplate.opsForValue().setIfAbsent(key, value);
		return isSaved != null && isSaved;
	}

	public String getSession(final String sessionId) {
		return this.redisTemplate.opsForValue().get(sessionId);
	}

	public void deleteSession(final String sessionId) {
		this.redisTemplate.delete(sessionId);
	}
}