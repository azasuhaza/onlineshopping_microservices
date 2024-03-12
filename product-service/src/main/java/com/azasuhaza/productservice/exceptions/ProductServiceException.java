package com.azasuhaza.productservice.exceptions;

import lombok.Data;

@Data
public class ProductServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;
	
	public ProductServiceException(String code, String msg) {
		this.errorCode=code;
		this.errorMessage=msg;
	}

}
