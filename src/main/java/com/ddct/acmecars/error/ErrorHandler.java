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
			String details = null;
			if (error.getClass().isAssignableFrom(FieldError.class)) {
				details = ((FieldError)error).getField();
			}
			errorCollection.getErrors().add(
				new ErrorResponse(error.getDefaultMessage(), details));
		}

		return new ResponseEntity<>( errorCollection, HttpStatus.BAD_REQUEST );
	}

}
