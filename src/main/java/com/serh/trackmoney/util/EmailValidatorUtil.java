package com.serh.trackmoney.util;

import com.serh.trackmoney.dto.annotation.EmailValidator;
import com.serh.trackmoney.exception.api.EmailNotValidException;

public class EmailValidatorUtil {
    private final static String EMAIL_PATTERN;

    static {
        EMAIL_PATTERN = EmailValidator.getEMAIL_PATTERN();
    }

    public static void validateOrElseThrow(final String email) {
        if (!email.matches(EMAIL_PATTERN)) {
            throw new EmailNotValidException("Email is incorrect.");
        }
    }
}
