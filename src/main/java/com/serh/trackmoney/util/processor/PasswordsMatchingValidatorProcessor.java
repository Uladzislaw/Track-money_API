package com.serh.trackmoney.util.processor;

import com.serh.trackmoney.dto.EntityDto;
import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.exception.api.PasswordsDontMatchException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Component("password")
public class PasswordsMatchingValidatorProcessor extends RegistrationProcessor {
    @Override
    public void validateOrElseThrow(final EntityDto dto) {
        UserDto userDto  = (UserDto) dto;
        if (isNull(userDto.getPassword()) || isNull(userDto.getMatchingPassword())) {
            throw new PasswordsDontMatchException("Passwords cannot be null");
        }
        if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            throw new PasswordsDontMatchException("Passwords doesn't match");
        }
        doProcess(userDto);
    }
}
