package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto implements EntityDto, Convertable<Category, CategoryDto> {

    @NotBlank
    private String name;
    @NotBlank
    private CategoryType type;

    @Override
    public Category toEntity() {
        return Category.builder()
                .name(name)
                .type(type)
                .build();
    }
}
