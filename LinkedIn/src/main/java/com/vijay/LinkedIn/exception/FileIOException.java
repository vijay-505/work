package com.vijay.LinkedIn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileIOException extends RuntimeException {
	
	public FileIOException(String message) {
		super(message);
	}

}
