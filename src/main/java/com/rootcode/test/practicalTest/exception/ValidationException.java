package com.rootcode.test.practicalTest.exception;

public class ValidationException extends BasicException {
    private static final int BAD_REQUEST = 400;
    private final String attribute;


    public ValidationException() {
        super(400, null);
        this.attribute = null;
    }

    public ValidationException(String errorCode, String attribute) {
        super(400, errorCode);
        this.attribute = attribute;
    }


    public String getAttribute() {
        return this.attribute;
    }


}
