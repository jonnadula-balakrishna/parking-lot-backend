package com.parkinglot.exceptions;

public class CustomException extends RuntimeException {

    private int errorCode; // Custom error code
    private String errorMessage; // Custom error message

    // Constructors
    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CustomException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    // Getters
    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return " Custom Exception{" + "errorCode=" + errorCode + ", errorMessage='" + errorMessage + '\'' + '}';
    }
}
