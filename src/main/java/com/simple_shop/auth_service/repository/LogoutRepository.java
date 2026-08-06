package com.simple_shop.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simple_shop.auth_service.persistence.LogoutEntity;

@Repository
public interface LogoutRepository extends JpaRepository<LogoutEntity, Long> {
	// Spring Data JPA automatically provides standard CRUD and Paging methods.
}
