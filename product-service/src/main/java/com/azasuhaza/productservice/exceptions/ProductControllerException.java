package com.azasuhaza.productservice.exceptions;

import lombok.Data;


@Data
public class ProductControllerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;

	public ProductControllerException(String code, String msg) {
		this.errorCode=code;
		this.errorMessage=msg;
	}
}
