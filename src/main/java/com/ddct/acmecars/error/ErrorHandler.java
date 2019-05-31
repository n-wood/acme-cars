package com.ddct.acmecars.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorCollection> handleBindingError( MethodArgumentNotValidException ex )
	{
		ErrorCollection errorCollection = new ErrorCollection();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			ErrorResponse.ErrorResponseBuilder errorBuilder =
				ErrorResponse.builder()
				.message(error.getDefaultMessage());
			if (error instanceof FieldError) {
				FieldError fieldError = (FieldError) error;
				errorBuilder.details("Property: " + fieldError.getField());
			}

			errorCollection.getErrors().add(errorBuilder.build());
		}

		return new ResponseEntity<>( errorCollection, HttpStatus.BAD_REQUEST );
	}

}
