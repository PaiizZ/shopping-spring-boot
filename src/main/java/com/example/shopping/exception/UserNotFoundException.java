package com.example.shopping.exception;

public class UserNotFoundException extends RuntimeException {
//    private String errorCode;
//    private String errorDescription;

//    public UserNotFoundException(String errorCode, String errorDescription) {
//        super();
//        this.errorCode = errorCode;
//        this.errorDescription = errorDescription;
//    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
