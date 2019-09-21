package com.serh.trackmoney.dto;

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

    @Override
    public Category toEntity() {
        return Category.builder()
                .name(name)
                .type(type)
                .build();
    }
}
