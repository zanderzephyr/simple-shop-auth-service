package com.simple_shop.auth_service.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simple_shop.auth_service.dto.AuthServiceErrorResponseDto;
import com.simple_shop.auth_service.dto.AuthServiceErrorResponseDto.AuthServiceErrorResponseDtoBuilder;

@RestControllerAdvice(assignableTypes = UserController.class)
public final class ControllerAdvice {

	public ControllerAdvice() {
		super();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<AuthServiceErrorResponseDto> handleHttpMessageNotReadableException(
			final HttpMessageNotReadableException exception) {

		final AuthServiceErrorResponseDto responseDto = new AuthServiceErrorResponseDtoBuilder()//
				.setDetail(exception.getMessage())//
				.setHttpState(HttpStatus.BAD_REQUEST.value())//
				.setMessage("Request contains bad content!")//
				.setTimestamp(Instant.now())//
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}
}
