package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Entity;

@FunctionalInterface
public interface Convertable<T extends Entity> {
    T toEntity();
}
