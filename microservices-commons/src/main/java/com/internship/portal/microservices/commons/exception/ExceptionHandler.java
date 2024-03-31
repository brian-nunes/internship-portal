package com.internship.portal.microservices.commons.exception;

import com.internship.portal.microservices.commons.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BaseBusinessException.class)
    public ResponseEntity<ErrorDTO> handleBaseBusinessException(final BaseBusinessException exception) {
        log.error("Erro: {}", exception.getMessage());
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(exception.getErrorCode());
        errorDTO.setMessage(exception.getErrorMessage());
        errorDTO.setStack(exception.getMessage());

        return new ResponseEntity<>(errorDTO, exception.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleUnknownException(final Exception exception, final HttpServletRequest request) {
        log.error("Erro: {}", exception.getMessage());
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("ERROR_USER_0000");
        errorDTO.setMessage("Unexpected Error");

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
