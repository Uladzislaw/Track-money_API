package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Entity;

public interface Convertable<T extends Entity, U extends EntityDto> {
    default T toEntity() {
        throw new UnsupportedOperationException(
                "Operation does not support by entity class");
    }

    default U toDto() {
        throw new UnsupportedOperationException(
                "Operation does not support by dto class");
    }
}
