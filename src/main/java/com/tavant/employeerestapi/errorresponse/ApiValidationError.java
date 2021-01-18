package com.tavant.employeerestapi.errorresponse;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApiValidationError extends ApiSubError implements Serializable{
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	private String object;
	private String field;
	private Object rejectedvalue;
	private String message;
	
	public ApiValidationError(String object, String field, Object rejectedvalue, String message) {
		super();
		this.object = object;
		this.field = field;
		this.rejectedvalue = rejectedvalue;
		this.message = message;
	}

	public ApiValidationError(String object, String message) {
		// TODO Auto-generated constructor stub
		this.object = object;
		this.message = message;
	}
}
