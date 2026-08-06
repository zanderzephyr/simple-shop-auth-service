package com.simple_shop.auth_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simple_shop.auth_service.exceptions.ItemNotFoundException;
import com.simple_shop.auth_service.exceptions.LoginRequestValidationException;
import com.simple_shop.auth_service.model.ItemAction;
import com.simple_shop.auth_service.model.LoginRequest;
import com.simple_shop.auth_service.persistence.AuthTransactionHandler;

@Service
public final class AuthService {
	private final AuthTransactionHandler itemTransactionHandler;

	public AuthService(final AuthTransactionHandler itemTransactionHandler) {
		super();

		this.itemTransactionHandler = itemTransactionHandler;
	}

	public Page<LoginRequest> getAllItems(final Pageable pageable) {
		return this.itemTransactionHandler.findAll(pageable);
	}

	public LoginRequest getItemById(final long id) {
		final LoginRequest item = this.findItemById(id);
		return item;
	}

	public LoginRequest createItem(final LoginRequest itemToCreate, final long userId) {
		AuthService.validateItemToCreate(itemToCreate);

		final LoginRequest finalizedItem = AuthTransactionHandler.finalizeItem(itemToCreate, userId);
		final LoginRequest createdItem = this.itemTransactionHandler.saveItem(finalizedItem, ItemAction.CREATE);
		return createdItem;
	}

	private static void validateItemToCreate(final LoginRequest itemToCreate) {
		if (itemToCreate.getId() != null) {
			throw new LoginRequestValidationException("An item to create must not have an ID!");
		}

		if (itemToCreate.getVersion() != null) {
			throw new LoginRequestValidationException("An item to create must not have a version!");
		}
	}

	public LoginRequest modifyItem(final LoginRequest itemToModify, final long userId) {
		// Ensure the item exists
		this.findItemById(itemToModify.getId());

		final LoginRequest finalizedItem = AuthTransactionHandler.finalizeItem(itemToModify, userId);

		final LoginRequest modifiedItem = this.itemTransactionHandler.saveItem(finalizedItem, ItemAction.MODIFY);

		return modifiedItem;
	}

	public void deleteItem(final long itemId, final long userId) {
		final LoginRequest item = this.itemTransactionHandler.deleteItem(itemId, userId);
		if (item == null) {
			throw new ItemNotFoundException(AuthService.createItemNotFoundMessage(itemId));
		}
	}

	private LoginRequest findItemById(final long itemId) {
		final LoginRequest item = this.itemTransactionHandler.findById(itemId);
		if (item == null) {
			throw new ItemNotFoundException(AuthService.createItemNotFoundMessage(itemId));
		}
		return item;
	}

	private static String createItemNotFoundMessage(final long itemId) {
		return "Didn't find item with ID " + itemId + "!";
	}
}
