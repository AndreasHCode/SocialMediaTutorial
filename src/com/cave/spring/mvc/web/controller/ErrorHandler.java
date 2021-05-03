package com.cave.spring.mvc.web.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(DataAccessException.class)
	public String handleDataBaseException(DataAccessException e) {
		System.out.println(e.getMessage());
		return "error";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException e) {
		System.out.println(e.getMessage());
		return "denied";
	}

}
