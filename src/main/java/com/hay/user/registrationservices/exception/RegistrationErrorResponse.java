package com.hay.user.registrationservices.exception;

public record RegistrationErrorResponse(String returnCode, String errorMessage, String errorReason) {
}