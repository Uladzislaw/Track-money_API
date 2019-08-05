package com.serh.trackmoney.service;

import com.serh.trackmoney.model.User;

import java.util.Optional;

public interface UserService extends CrudService<User> {

    Optional<User> findOneByEmail(String email);
}
