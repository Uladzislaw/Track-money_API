package com.serh.trackmoney.util;

import com.serh.trackmoney.exception.api.NullableFieldException;

import java.lang.reflect.Field;
import java.util.Arrays;

import static java.util.Objects.isNull;

public class NullableFieldInterceptor {
    public static void interceptNullFieldAndThrow(final Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            try {
                if (isNull(field.get(object))) {
                    throw new NullableFieldException("All fields must be not null");
                }
            } catch (IllegalAccessException ignore) {
            }
        });
    }
}
