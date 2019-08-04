package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Entity;

public interface Convertable<T extends Entity> {
    T toEntity();

    void convert(T entity);
}
