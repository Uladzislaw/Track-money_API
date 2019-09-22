package com.serh.trackmoney.util;

import com.serh.trackmoney.annotation.PutNullable;
import com.serh.trackmoney.exception.api.NullableFieldException;

import java.lang.reflect.Field;

import static java.util.Objects.isNull;

public class NullableFieldInterceptor {
    public static void interceptNullFieldAndThrow(final Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(PutNullable.class) == null) {
                field.setAccessible(true);
                try {
                    if (isNull(field.get(object))) {
                        throw new NullableFieldException("All fields must be not null");
                    }
                } catch (IllegalAccessException ignore) {
                }
            }
        }
    }
}
