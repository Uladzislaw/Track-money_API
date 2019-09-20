package com.serh.trackmoney.controller;

import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private Supplier<CategoryNotFoundException> categoryNotFoundException
            = () -> new CategoryNotFoundException("Category not found.");

    @GetMapping(value = "/{id}")
    public Category getOne(@PathVariable final Long id) {
        return categoryService.findOneById(id)
                .orElseThrow(categoryNotFoundException);
    }

    @GetMapping(value = "/specific/{userId}")
    public List<Category> getSpecificCategoriesForUser(
            @PathVariable final Long userId) {
        return categoryService.findSpecific(userId);
    }
}
