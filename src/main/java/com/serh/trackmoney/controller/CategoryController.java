package com.serh.trackmoney.controller;

import com.serh.trackmoney.dto.CategoryDto;
import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.function.Supplier;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

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

    @GetMapping(value = "/user/{id}")
    public List<Category> getAllForUser(@PathVariable final Long id) {
        return categoryService.findAllForUser(id);
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity createNewCategory(@PathVariable final Long userId,
                                            @RequestBody @Valid final CategoryDto categoryDto) {
        return created(URI.create("/api/v1/users/"
                + categoryService.create(userId, categoryDto).getId()))
                .build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDto> update(@PathVariable final Long id,
                                              @RequestBody @Valid final CategoryDto categoryDto) {
        return ok(categoryService.update(id, categoryDto).toDto());
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDto> updateByNonNull(@PathVariable final Long id,
                                                       @RequestBody final CategoryDto categoryDto) {
        return ok(categoryService.updateByNonNullFields(id, categoryDto).toDto());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable final Long id) {
        categoryService.delete(id);
    }
}
