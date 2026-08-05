package com.simple_shop.auth_service.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.simple_shop.auth_service.model.ItemAction;
import com.simple_shop.auth_service.model.ItemHistoryState;
import com.simple_shop.auth_service.model.LoginRequest;
import com.simple_shop.auth_service.model.ItemHistoryState.ItemHistoryStateBuilder;
import com.simple_shop.auth_service.model.LoginRequest.LoginRequestBuilder;
import com.simple_shop.auth_service.repository.ItemHistoryStateEntityRepository;
import com.simple_shop.auth_service.repository.LoginsEntityRepository;
import com.simple_shop.auth_service.utils.AuthServiceUtils;

@Component
public class ItemTransactionHandler {
	private final LoginsEntityRepository itemRepository;

	private final ItemHistoryStateEntityRepository itemHistoryStateRepository;

	public ItemTransactionHandler(//
			final LoginsEntityRepository itemRepository, final ItemHistoryStateEntityRepository historyRepository) {

		super();

		this.itemRepository = itemRepository;
		this.itemHistoryStateRepository = historyRepository;
	}

	@Transactional(readOnly = true)
	public Page<LoginRequest> findAll(final Pageable pageable) {
		final Page<LoginEntity> entityPage = this.itemRepository.findAll(pageable);

		return entityPage.map(PersistenceMapper::toDomain);
	}

	@Transactional(readOnly = true)
	public LoginRequest findById(final long id) {
		final LoginEntity entity = this.itemRepository.findById(id).orElse(null); //
		return entity == null ? null : PersistenceMapper.toDomain(entity);
	}

	@Transactional
	public LoginRequest saveItem(final LoginRequest item, final ItemAction itemAction) {
		final LoginEntity itemEntity = PersistenceMapper.toEntity(item);

		/*
		 * Flushing makes sure, the Id and Version fields are properly created / updated
		 * before going on.
		 */
		final LoginEntity savedItemEntity = this.itemRepository.saveAndFlush(itemEntity);

		final LoginRequest savedItem = PersistenceMapper.toDomain(savedItemEntity);

		final ItemHistoryState itemHistoryState = ItemTransactionHandler.buildItemHistoryState(savedItem, itemAction);
		final ItemHistoryStateEntity itemHistoryStateEntity = PersistenceMapper.toEntity(itemHistoryState);
		this.itemHistoryStateRepository.save(itemHistoryStateEntity);

		return savedItem;
	}

	@Transactional
	public LoginRequest deleteItem(final long itemId, final long userId) {
		final LoginEntity existingItemEntity = this.itemRepository.findById(itemId).orElse(null);
		if (existingItemEntity == null) {
			return null;
		}

		final LoginRequest existingItem = PersistenceMapper.toDomain(existingItemEntity);

		/*
		 * The main itemEntity remains unmutated so it can be cleanly deleted via its
		 * current database version.
		 */
		final LoginRequest itemDeletionState = ItemTransactionHandler.finalizeItem(existingItem, userId);
		final ItemHistoryState itemHistoryState = ItemTransactionHandler.buildItemHistoryState(itemDeletionState,
				ItemAction.DELETE);
		final ItemHistoryStateEntity itemHistoryStateEntity = PersistenceMapper.toEntity(itemHistoryState);

		this.itemRepository.delete(existingItemEntity);
		this.itemHistoryStateRepository.save(itemHistoryStateEntity);

		return existingItem;
	}

	private static ItemHistoryState buildItemHistoryState(final LoginRequest item, final ItemAction action) {
		return new ItemHistoryStateBuilder() //
				.setId(null) //
				.setItemId(item.getId()) //
				.setTitle(item.getTitle()) //
				.setMediaType(item.getMediaType()) //
				.setStock(item.getStock()) //
				.setPrice(item.getPrice()) //
				.setImageUrl(item.getImageUrl()) //
				.setLastChange(item.getLastChange()) //
				.setLastChangedBy(item.getLastChangedBy()) //
				.setAction(action) //
				.setVersion(item.getVersion())//
				.build();
	}

	public static LoginRequest finalizeItem(final LoginRequest item, final long userId) {
		return LoginRequestBuilder.initFrom(item) //
				.setLastChange(AuthServiceUtils.createNow()) //
				.setLastChangedBy(userId) //
				.build();
	}
}
