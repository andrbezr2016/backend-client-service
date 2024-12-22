package com.andrbezr2016.backend.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BackendClientExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorResponse> handleClientException(ClientException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(exception.getHttpStatus().value());
        errorResponse.setMessage(exception.getMessage());
        log.error(errorResponse.toString());
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorResponse> handleServerException(ServerException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(exception.getHttpStatus().value());
        errorResponse.setMessage(exception.getMessage());
        log.error(errorResponse.toString(), exception);
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }
}
