package com.example.demo.exception;

public class ResourceNotFound extends RuntimeException{
	String resourceName;
	String fieldName;
	int resourceId;
	
	public ResourceNotFound(String resourceName,String fieldName,int resourceId) {
		super(String.format("%s with %s : %s is not found",resourceName,fieldName,resourceId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.resourceId = resourceId;
	}
}
