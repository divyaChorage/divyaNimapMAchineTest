package com.example.demo.customException;

public class NullCategoryException extends RuntimeException {
    public NullCategoryException(String message) {
        super(message);
    }
}
