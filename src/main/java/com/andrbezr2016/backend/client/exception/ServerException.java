package com.andrbezr2016.backend.client.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServerException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ServerException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
