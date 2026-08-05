package com.simple_shop.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simple_shop.auth_service.persistence.LoginEntity;

@Repository
public interface LoginsEntityRepository extends JpaRepository<LoginEntity, Long> {
	// Spring Data JPA automatically provides standard CRUD and Paging methods.
}
