package com.simple_shop.auth_service.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Login")
public class LoginEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	/**
	 * In UTC.
	 */
	@Column(name = "Timestamp", nullable = false)
	private LocalDateTime timestamp;

	@Column(name = "Username", length = 30, nullable = false)
	private String username;

	@Column(name = "UserId", nullable = true)
	private Long userId;

	@Column(name = "Success", nullable = false)
	private boolean success;

	public LoginEntity() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(final LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}
}