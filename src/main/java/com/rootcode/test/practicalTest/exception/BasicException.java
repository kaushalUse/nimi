package com.rootcode.test.practicalTest.exception;

public class BasicException extends Exception {
    protected final int httpStatus;
    protected final String errorCode;

    public BasicException(int httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorMessage;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
