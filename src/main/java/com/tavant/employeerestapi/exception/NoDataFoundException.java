package com.tavant.employeerestapi.exception;

public class NoDataFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoDataFoundException(String message) {
		super(message);
		
	}
	
	@Override
	public String toString() {
		return super.toString() ;
	}

}
