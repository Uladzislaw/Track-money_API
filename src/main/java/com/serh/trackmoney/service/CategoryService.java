package com.serh.trackmoney.service;

import com.serh.trackmoney.dto.CategoryDto;
import com.serh.trackmoney.model.Category;

import java.util.List;

public interface CategoryService extends CrudService<Category> {
    Category create(Long userId, CategoryDto categoryDto);

    List<Category> findSpecific(Long userId);

    Category updateByNonNullFields(Long id, CategoryDto categoryDto);

    Category update(Long id, CategoryDto categoryDto);

    void delete(Long id);

    List<Category> findAllForUser(Long id);
}
