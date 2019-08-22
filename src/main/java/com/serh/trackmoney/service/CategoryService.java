package com.serh.trackmoney.service;

import com.serh.trackmoney.model.Category;

import java.util.List;

public interface CategoryService extends CrudService<Category> {
    List<Category> findSpecific(Long userId);
}
