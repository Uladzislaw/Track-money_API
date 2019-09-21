package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.dto.CategoryDto;
import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.repository.CategoryRepository;
import com.serh.trackmoney.repository.UserRepository;
import com.serh.trackmoney.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;
import static java.util.Set.of;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final Supplier<CategoryNotFoundException> categoryNotFoundException
            = () -> new CategoryNotFoundException("Category wasn't found");


    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findOneById(final Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category create(final Long userId, final CategoryDto categoryDto) {
        String name = categoryDto.getName();
        categoryDto.setName(name.substring(0, 1).toUpperCase()
                .concat(name.toLowerCase().substring(1)));
        Category category = categoryRepository.findByName(categoryDto.getName());
        if (nonNull(category)) {
            category.getUsers().add(userRepository.findById(userId)
                    .orElseThrow(UserNotFoundException::new));
            return category;
        }
        Category newCategory = categoryDto.toEntity();
        newCategory.setUsers(of(userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new)));
        return save(newCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findSpecific(final Long userId) {
        return categoryRepository.findByIdGreaterThanAndUsersIs(9L,
                userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public Category updateByNonNullFields(final Long id, final CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(categoryNotFoundException);
        if (nonNull(categoryDto.getName())) {
            category.setName(categoryDto.getName());
        }
        if (nonNull(categoryDto.getType())) {
            category.setType(categoryDto.getType());
        }
        return update(category);
    }

    @Override
    public Category update(final Long id, final CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(categoryNotFoundException);
        category.setName(categoryDto.getName());
        category.setType(categoryDto.getType());
        return update(category);
    }

    @Override
    public Category update(final Category category) {
        return save(category);
    }

    @Override
    public Category save(final Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllForUser(final Long id) {
        return categoryRepository.findAllByUserId(id);
    }

    @Override
    public void delete(final Long id) {
        categoryRepository.deleteById(id);
    }
}
