package com.blogApp.blogging_api.exception;

import lombok.Data;

@Data
public class BloggingException extends RuntimeException {
	
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	
	public BloggingException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
