package com.ty.store.storespring.exception;

public class NoSuchIdFoundException extends RuntimeException{

	String message="No Such Id Found in Database";
	
	@Override
	public String getMessage() {
		return message;
	}

	public NoSuchIdFoundException() {
		
	}
	
	public NoSuchIdFoundException(String message) {
		this.message = message;
	}
	
	

}
