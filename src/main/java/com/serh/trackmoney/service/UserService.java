package com.serh.trackmoney.service;

import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService extends CrudService<User> {

    Page<User> findAll(int page, int size, String sort);

    User save(UserDto userDto) throws UserAlreadyExistsException;

    Optional<User> findOneByEmail(String email);

    User update(Long id, UserDto userDto);

    User updateByNonNullFields(Long id, UserDto userDto);

    User makeInactive(Long id);
}
