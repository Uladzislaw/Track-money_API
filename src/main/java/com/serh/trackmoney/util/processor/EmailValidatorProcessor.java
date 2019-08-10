package com.serh.trackmoney.util.processor;

import com.serh.trackmoney.dto.EntityDto;
import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.exception.api.EmailNotValidException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Component("email")
public class EmailValidatorProcessor extends RegistrationProcessor {
    private final static String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}" +
            "(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    public void validateOrElseThrow(final EntityDto dto) {
        UserDto userDto = (UserDto) dto;
        if (isNull(userDto.getEmail())) {
            throw new EmailNotValidException("Email cannot be null");
        }
        if (!userDto.getEmail().matches(EMAIL_PATTERN)) {
            throw new EmailNotValidException("Email is incorrect");
        }
        doProcess(userDto);
    }
}
