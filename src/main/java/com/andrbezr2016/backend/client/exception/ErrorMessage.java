package com.andrbezr2016.backend.client.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    PRODUCT_NOT_EXIST("Product with id: %s doesn't exist"),
    SOMETHING_WRONG("Something was wrong!");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
