package com.rootcode.test.practicalTest.validator;

public class CustomTransactionException extends Exception  {

    public CustomTransactionException(String errorMessage) {
        super(errorMessage);
    }
}