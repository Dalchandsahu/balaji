package com.blogAPI.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogAPI.PayLoad.ApiResponce;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFountExceptionHandeler(ResourceNotFoundException ex){
		
		String message  = ex.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, false);
		
		return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.NOT_FOUND); 
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Integer>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fielName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fielName, message);
			
		});
		return null;
		
	}
}
