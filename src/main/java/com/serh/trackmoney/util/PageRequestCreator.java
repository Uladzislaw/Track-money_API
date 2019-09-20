package com.serh.trackmoney.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.PageRequest.of;

public class PageRequestCreator {
    public static PageRequest createPageRequest(final int page, final int size,
                                          final String sort) {
        String[] sortParams = sort.split(",");
        return sortParams.length > 1 && sortParams[1].equals("desc")
                ? of(page, size, Sort.by(sortParams[0]).descending())
                : of(page, size, Sort.by(sortParams[0]));
    }
}
