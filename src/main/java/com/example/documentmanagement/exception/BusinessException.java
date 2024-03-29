package com.example.documentmanagement.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
    private String key;
    private final String[] args;

    public BusinessException(String key) {
        super(key);
        this.key = key;
        args = new String[0];
    }

    public BusinessException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}
