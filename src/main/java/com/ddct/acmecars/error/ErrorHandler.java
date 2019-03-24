package com.ddct.acmecars.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleBindingError( MethodArgumentNotValidException ex )
	{
		String errMsg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		
		ErrorResponse errorResponse = new ErrorResponse( errMsg );
		
		return new ResponseEntity<>( errorResponse, HttpStatus.BAD_REQUEST );
	}

}
