package com.tavant.employeerestapi.exception;

public class BlankObjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public BlankObjectException(String message) {
		super(message);
		
	}
	
	@Override
	public String toString() {
		return super.toString() ;
	}

}
