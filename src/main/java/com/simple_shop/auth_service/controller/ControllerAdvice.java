package com.simple_shop.auth_service.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simple_shop.auth_service.dto.ServiceErrorResponseDto;
import com.simple_shop.auth_service.exceptions.InvalidMessageException;

@RestControllerAdvice(assignableTypes = { UserController.class, BackendController.class, AdminController.class })
public final class ControllerAdvice {

	public ControllerAdvice() {
		super();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ServiceErrorResponseDto> handleHttpMessageNotReadableException(
			final HttpMessageNotReadableException exception) {

		final ServiceErrorResponseDto responseDto = new ServiceErrorResponseDto()//
				.setDetail(exception.getMessage())//
				.setHttpState(HttpStatus.BAD_REQUEST.value())//
				.setMessage("Request contains bad content!")//
				.setTimestamp(Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}

	@ExceptionHandler(InvalidMessageException.class)
	public ResponseEntity<ServiceErrorResponseDto> handleInvalidMessageException(
			final InvalidMessageException exception) {

		final ServiceErrorResponseDto responseDto = new ServiceErrorResponseDto()//
				.setDetail(exception.getMessage())//
				.setHttpState(HttpStatus.BAD_REQUEST.value())//
				.setMessage("Invalid message!")//
				.setTimestamp(Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}
}
