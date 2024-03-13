package com.azasuhaza.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.azasuhaza.productservice.exceptions.CroissantsNameException;
import com.azasuhaza.productservice.exceptions.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(CroissantsNameException.class)
	public ResponseEntity<String> handleCroissantsInput(CroissantsNameException crInputException){
		
		return new ResponseEntity<String>("searching for croisants not allowed", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleZeroId(NoSuchElementException emptyIdException){
		return new ResponseEntity<String>("searching for non-existant id is not allowed", HttpStatus.NOT_ACCEPTABLE);
	}
}
