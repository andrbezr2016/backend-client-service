package com.andrbezr2016.backend.client.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    PRODUCT_NOT_EXIST("Product with id: %s doesn't exist"),
    PROBLEM_WITH_INTERNAL("Something was wrong during messaging with internal service");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
