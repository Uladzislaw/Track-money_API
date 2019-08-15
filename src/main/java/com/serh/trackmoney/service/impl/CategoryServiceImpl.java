package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Optional<Category> findOneById(final Long id) {
        return Optional.empty();
    }

    @Override
    public Category update(final Category entity) {
        return null;
    }

    @Override
    public Category save(final Category entity) throws UserAlreadyExistsException {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }
}
