package com.andrbezr2016.backend.client.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ClientExceptionResponse> clientExceptionHandler(ClientException exception) {
        ClientExceptionResponse clientExceptionResponse = new ClientExceptionResponse();
        clientExceptionResponse.setStatus(exception.getHttpStatus().value());
        clientExceptionResponse.setMessage(exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(clientExceptionResponse);
    }
}
