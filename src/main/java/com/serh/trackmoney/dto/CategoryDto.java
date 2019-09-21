package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto implements EntityDto, Convertable<Category, CategoryDto> {

    @NonNull
    private String name;
    @NonNull
    private CategoryType type;

    @Override
    public Category toEntity() {
        return Category.builder()
                .name(name)
                .type(type)
                .build();
    }
}
