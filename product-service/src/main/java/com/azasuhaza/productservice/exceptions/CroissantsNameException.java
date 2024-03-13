package com.azasuhaza.productservice.exceptions;

import lombok.Data;

@Data
public class CroissantsNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	public CroissantsNameException(String code, String msg) {
		this.errorCode=code;
		this.errorMessage=msg;
	}
	
	
}
