package com.simple_shop.auth_service.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FailedLogin")
public class FailedLoginEntity {
	@Id
	@Column(name = "UserId", nullable = false)
	private long userId;

	@Column(name = "FailedLoginCount", nullable = false)
	private byte failedLoginCount;

	public FailedLoginEntity() {
		super();
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}

	public byte getFailedLoginCount() {
		return this.failedLoginCount;
	}

	public void setFailedLoginCount(final byte failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}
}