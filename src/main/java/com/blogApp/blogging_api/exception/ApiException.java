package com.blogApp.blogging_api.exception;

public class ApiException extends RuntimeException {

	public ApiException(String message) {
		super(message);
	}
	
	
	public ApiException() {
		super();
	}
	
	
}
