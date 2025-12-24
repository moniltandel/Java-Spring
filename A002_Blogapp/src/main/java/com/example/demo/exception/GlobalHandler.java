package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {

   
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<APIResponse> ResourceNotFoundHandler(ResourceNotFound e) {
		APIResponse response = new APIResponse(e.getMessage(),"false");
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
