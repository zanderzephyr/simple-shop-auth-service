package com.simple_shop.auth_service.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simple_shop.auth_service.auth.persistence.ItemHistoryStateEntity;

@Repository
public interface ItemHistoryStateEntityRepository extends JpaRepository<ItemHistoryStateEntity, Long> {
	// Spring Data JPA automatically provides standard CRUD and Paging methods.
}
