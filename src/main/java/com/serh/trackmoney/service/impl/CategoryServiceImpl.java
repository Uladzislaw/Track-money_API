package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.repository.CategoryRepository;
import com.serh.trackmoney.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findOneById(final Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category update(final Category category) {
        return null;
    }

    @Override
    public Category save(final Category category) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return null;
    }
}
