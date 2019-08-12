package com.serh.trackmoney.service;

import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.model.Entity;
import com.serh.trackmoney.model.User;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends Entity> {

    Optional<T> findOneById(Long id);

    T update(T entity);

    T save(T entity) throws UserAlreadyExistsException;

    User makeInactive(Long id);

    List<T> findAll();
}
