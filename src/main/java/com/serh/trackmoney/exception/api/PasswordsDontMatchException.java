package com.serh.trackmoney.exception.api;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException(final String s) {
        super(s);
    }
}
