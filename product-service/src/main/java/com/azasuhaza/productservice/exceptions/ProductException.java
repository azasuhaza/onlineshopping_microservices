package com.azasuhaza.productservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public ProductException(String errorCode, String message) {
		super(message);
		this.errorCode=errorCode;
	}
}
