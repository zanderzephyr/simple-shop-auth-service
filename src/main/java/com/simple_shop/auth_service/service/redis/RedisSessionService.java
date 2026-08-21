package com.simple_shop.auth_service.service.redis;

import java.util.UUID;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.simple_shop.auth_service.service.redis.RedisSessionData.RedisSessionDataBuilder;

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
	 * returned session ID is the key in Redis and the string representation of the
	 * sessionData parameter the value in Redis.)
	 *
	 * @param  sessionData
	 *
	 * @return
	 */
	public String createSession(final RedisSessionData sessionData) {
		boolean isSaved = false;
		int iteration = 0;
		String sessionId = null;

		while (!isSaved) {
			iteration++;
			if (iteration > RedisSessionService.MAX_CREATION_BOUNDARY) {
				throw new IllegalStateException("Redis session highly saturated! Failed to create session!");
			}

			sessionId = UUID.randomUUID().toString();
			final String sessionDataString = this.sessionDataToString(sessionData);
			isSaved = this.createSession(sessionId, sessionDataString);
		}

		return sessionId;
	}

	private boolean createSession(final String key, final String value) {
		final Boolean isSaved = this.redisTemplate.opsForValue().setIfAbsent(key, value);
		return isSaved != null && isSaved;
	}

	/**
	 * @param  sessionId
	 *
	 * @return           The redis session data or null if no session exists in the
	 *                   redis cache.
	 */
	public RedisSessionData getSession(final String sessionId) {
		final String sessionDataString = this.redisTemplate.opsForValue().get(sessionId);
		return this.stringToSessionData(sessionDataString);
	}

	public void deleteSession(final String sessionId) {
		this.redisTemplate.delete(sessionId);
	}

	private String sessionDataToString(final RedisSessionData sessionData) {
		final StringBuilder sb = new StringBuilder();

		sb.append(sessionData.getUserId());

		return sb.toString();
	}

	private RedisSessionData stringToSessionData(final String sessionDataString) {
		final RedisSessionData redisSessionData;

		if (sessionDataString != null) {
			final long userId = Long.parseLong(sessionDataString);
			redisSessionData = new RedisSessionDataBuilder()//
					.setUserId(userId)//
					.build();
		} else {
			redisSessionData = null;
		}

		return redisSessionData;
	}
}