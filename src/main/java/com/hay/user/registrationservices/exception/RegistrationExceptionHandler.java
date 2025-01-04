package com.hay.user.registrationservices.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class RegistrationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Object> handleMethodNotSupportedException(MethodArgumentNotValidException ex) {
		log.error(ex.getMessage());
		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> errors = bindingResult.getAllErrors();
		FieldError fieldError = bindingResult.getFieldError();
		return new ResponseEntity<>(new ValidationErrorResponse("Validation failed", fieldError), HttpStatus.BAD_REQUEST);
	}

}