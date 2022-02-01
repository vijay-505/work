package com.vijay.LinkedIn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class FileErrorException extends RuntimeException {
	
	public FileErrorException(String message) {
		super(message);
	}

}
