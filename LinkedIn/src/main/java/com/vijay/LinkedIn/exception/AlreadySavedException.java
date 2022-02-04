package com.vijay.LinkedIn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadySavedException extends RuntimeException {
	
	public AlreadySavedException(String message) {
		super(message);
	}

}
