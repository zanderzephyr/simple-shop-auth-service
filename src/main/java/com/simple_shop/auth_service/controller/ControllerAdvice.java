package com.simple_shop.auth_service.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simple_shop.auth_service.dto.ServiceErrorResponseDto;
import com.simple_shop.auth_service.exceptions.InvalidMessageException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice(assignableTypes = { UserController.class, BackendController.class, AdminController.class })
public final class ControllerAdvice {

	public ControllerAdvice() {
		super();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ServiceErrorResponseDto> handleHttpMessageNotReadableException(
			final HttpMessageNotReadableException exception) {

		return ControllerAdvice.createCommonBadRequestResponse(exception);
	}

	@ExceptionHandler(InvalidMessageException.class)
	public ResponseEntity<ServiceErrorResponseDto> handleInvalidMessageException(
			final InvalidMessageException exception) {

		return ControllerAdvice.createCommonBadRequestResponse(exception);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ServiceErrorResponseDto> handleConstraintViolationException(
			final ConstraintViolationException exception) {

		return ControllerAdvice.createCommonBadRequestResponse(exception);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ServiceErrorResponseDto> handleMethodArgumentNotValidException(
			final MethodArgumentNotValidException exception) {

		return ControllerAdvice.createCommonBadRequestResponse(exception);
	}

	private static ResponseEntity<ServiceErrorResponseDto> createCommonBadRequestResponse(final Throwable throwable) {
		final ServiceErrorResponseDto responseDto = new ServiceErrorResponseDto()//
				.setDetail(throwable.getMessage())//
				.setHttpState(HttpStatus.BAD_REQUEST.value())//
				.setMessage("Invalid message!")//
				.setTimestamp(Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}
}
