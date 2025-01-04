package com.hay.user.registrationservices.exception;

import org.springframework.validation.FieldError;


public record ValidationErrorResponse(String message, FieldError errors){}
