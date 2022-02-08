package com.vijay.LinkedIn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FollowerAlreadyExistException extends RuntimeException {
	
	public FollowerAlreadyExistException(String message) {
		super(message);
	}

}
