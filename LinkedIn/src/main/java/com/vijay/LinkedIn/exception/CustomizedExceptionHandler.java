package com.vijay.LinkedIn.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vijay.LinkedIn.util.ExceptionResponse;


@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<Object> handleUserAlreadyExistsException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FileErrorException.class)
	public final ResponseEntity<Object> handleFileErrorException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(FileIOException.class)
	public final ResponseEntity<Object> handleFileIOException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@ExceptionHandler(RuntimeException.class)
//	public final ResponseEntity<Object> handleRuntimeExceptions
//	(Exception ex, WebRequest request){
//		
//		ExceptionResponse exceptionResponse = 
//				new ExceptionResponse(ex.getMessage(), 
//				request.getDescription(false));
//		
//		return new ResponseEntity(exceptionResponse,HttpStatus.OK);
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Object> handleAllExceptions
//	(Exception ex, WebRequest request){
//		
//		ExceptionResponse exceptionResponse = 
//				new ExceptionResponse(ex.getMessage(), 
//				request.getDescription(false));
//		
//		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders header, HttpStatus status, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"Validation Faild", ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		
	}
}
