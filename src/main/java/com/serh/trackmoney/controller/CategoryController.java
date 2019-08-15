package com.serh.trackmoney.controller;

import com.serh.trackmoney.controller.helper.RelatedLinkCreatorHelper;
import com.serh.trackmoney.dto.CategoryDto;
import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final RelatedLinkCreatorHelper linkCreator;

    private Supplier<CategoryNotFoundException> categoryNotFoundException
            = () -> new CategoryNotFoundException("Category not found.");

    @GetMapping(value = "/{id}")
    public Resource<CategoryDto> getOne(@PathVariable final Long id) {
        return addResource(categoryService.findOneById(id)
                .orElseThrow(categoryNotFoundException));
    }

    private Resource<CategoryDto> addResource(final Category category) {
        return new Resource<>(category.toDto(),
                linkCreator.createSimpleLinkListForCategory(category));
    }
}
