package com.serh.trackmoney.util;

import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.exception.api.NullableFieldException;

import static java.util.Objects.isNull;

public class NullableFieldInterceptor {
    public static void interceptNullFieldAndThrow(final UserDto userDto) {
        if (isNull(userDto.getEmail()) || isNull(userDto.getPassword())
                || isNull(userDto.getRole()) || isNull(userDto.getState())) {
            throw new NullableFieldException("All fields must be not null");
        }
    }

    public static void interceptNullFieldAndThrow(final ConsumptionDto consumptionDto) {
        if (isNull(consumptionDto.getAmount()) || isNull(consumptionDto.getCategory())
                || isNull(consumptionDto.getCurrency())) {
            throw new NullableFieldException("All fields must be not null");
        }
    }
}
