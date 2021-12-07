package com.rootcode.test.practicalTest.exception;

public class TansactionException  extends Exception {
        private final String failure;


        public TansactionException(String failure) {
            this.failure = failure;
        }

        public String getFailure() {
            return failure;
        }

    }

