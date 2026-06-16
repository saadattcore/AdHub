package com.adhub.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RestControllerAdvice
public class GlobalExceptionHandler {

	//@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception e) {		
				
		System.out.println("Global Exception");
		ResponseEntity<String> response = ResponseEntity
				.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		return response;
	}
}
