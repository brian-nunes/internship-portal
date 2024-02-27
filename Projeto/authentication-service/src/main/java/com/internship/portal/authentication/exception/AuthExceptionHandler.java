package com.internship.portal.authentication.exception;

import com.internship.portal.authentication.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseBusinessException.class)
    public ResponseEntity<ErrorDTO> handleBaseBusinessException(final BaseBusinessException exception) {
        log.error("Erro: {}", exception.getMessage());
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(exception.getErrorCode());
        errorDTO.setMessage(exception.getErrorMessage());
        errorDTO.setStack(exception.getMessage());

        return new ResponseEntity<>(errorDTO, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleUnknownException(final Exception exception, final HttpServletRequest request) {
        log.error("Erro generico: {}", exception.getMessage());
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("ERROR_AUTHENTICATION_0000");
        errorDTO.setMessage("Unexpected Error");
        if(log.isDebugEnabled()){
         errorDTO.setStack(exception.getMessage());
        }

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
