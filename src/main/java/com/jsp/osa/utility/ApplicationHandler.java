package com.jsp.osa.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationHandler 
{
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String errorMessage, String rootCause)
	{
		return ResponseEntity.status(status)
				.body(new ErrorStructure<String>()
						.setStatus(status.value())
						.setErrorMessage(errorMessage)
						.setRootCause(rootCause));
	}
}
