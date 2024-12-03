package com.blog_app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog_app.payloads.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	
@ExceptionHandler(ResourceNotFoundException.class)	
public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception)
{
	
	String message = exception.getMessage();
	
	ApiResponse response=new ApiResponse(message,false);
	
	return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
}

// exception for user validation 
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String,String>>userValidationExceptions(MethodArgumentNotValidException ex)	
{
		
	Map<String,String>map=new HashMap<>();
	
	ex.getBindingResult().getAllErrors().forEach(err->
	
	{
		String fieldname = ((FieldError) err).getField(); //typcast into Fielderror and then get filed name
		String defaultMessage = err.getDefaultMessage();
		map.put(fieldname, defaultMessage);
	}
	);
	
	return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	
}



@ExceptionHandler(ApiException.class)	
public ResponseEntity<ApiResponse> apiException(ApiException exception)
{

String message = exception.getMessage();

ApiResponse response=new ApiResponse(message,false);

return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	
}


//for unique email entery  DataIntegrityViolationException














}






