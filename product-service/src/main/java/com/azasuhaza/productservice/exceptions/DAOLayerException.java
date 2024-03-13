package com.azasuhaza.productservice.exceptions;

import lombok.Data;

@Data
public class DAOLayerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	public DAOLayerException(String code, String msg) {
		this.errorCode=code;
		this.errorMessage=msg;
	}
}
