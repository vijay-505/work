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

	@ExceptionHandler(PermissionDeniedException.class)
	public final ResponseEntity<Object> handlePermissionDeniedException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(AlreadyAppliedException.class)
	public final ResponseEntity<Object> handleAlreadyAppliedException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(AlreadySavedException.class)
	public final ResponseEntity<Object> handleAlreadySavedException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(FollowerAlreadyExistException.class)
	public final ResponseEntity<Object> handleFollowerAlreadyExistException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(FollowerNotExistException.class)
	public final ResponseEntity<Object> handleFollowerNotExistException
	(Exception ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders header, HttpStatus status, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"Validation Faild", ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		
	}
}
