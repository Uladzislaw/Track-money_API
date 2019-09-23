package com.serh.trackmoney.dto;

import com.serh.trackmoney.annotation.PutNullable;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto implements EntityDto, Convertable<Category, CategoryDto> {

    @NotNull
    private String name;
    @NotNull
    private CategoryType type;

    @PutNullable
    private Boolean isStandard;

    @Override
    public Category toEntity() {
        return Category.builder()
                .name(name)
                .type(type)
                .isStandard(isStandard)
                .build();
    }
}
