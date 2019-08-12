package com.serh.trackmoney.util;

import com.serh.trackmoney.exception.api.PageRequestException;
import com.serh.trackmoney.model.Entity;
import com.serh.trackmoney.model.User;

import java.lang.reflect.Field;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class PaginationQueryErrorInterceptor {
    public static void interceptIncorrectDataAndThrow(final int page,
                                                      final int size,
                                                      final String sort) {
        List<String> fieldNames = getFieldsName(User.class);
        fieldNames.addAll(getFieldsName(Entity.class));
        if (page <= 0 || size <= 0
                || !fieldNames.contains(sort.split(",")[0])) {
            throw new PageRequestException("Page request contains invalid data");
        }
    }

    private static List<String> getFieldsName(Class entity) {
        return stream(entity.getDeclaredFields())
                .map(Field::getName)
                .collect(toList());
    }
}
