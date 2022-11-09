package com.junit.handler;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentControllerAdvice {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public String entityNotFoundHandler(EntityNotFoundException exp) {
		return exp.getMessage();
	}
}
