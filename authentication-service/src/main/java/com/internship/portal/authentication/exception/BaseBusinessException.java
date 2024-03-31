package com.internship.portal.authentication.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseBusinessException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public BaseBusinessException(String errorCode, String errorMessage, HttpStatus httpStatus, Exception e) {
        super(e);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public BaseBusinessException(String errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
