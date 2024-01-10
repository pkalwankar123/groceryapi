package com.questionpro.grocery.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.questionpro.grocery.dto.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(Exception.class)
 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 public ResponseEntity<Response> handleException(Exception ex) {
	 String message=ex.getMessage();
		Response apiResponse= new Response(500,message,true,null);
     return new ResponseEntity<Response>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
 }
 

 @ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		Response apiResponse= new Response(404,message,true,null);
		
		return new ResponseEntity<Response>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response> handleApiException(ApiException ex){
		
		String message=ex.getMessage();
		Response apiResponse= new Response(400,message,true,null);
		
		return new ResponseEntity<Response>(apiResponse,HttpStatus.BAD_REQUEST);
		
	}

 
}

