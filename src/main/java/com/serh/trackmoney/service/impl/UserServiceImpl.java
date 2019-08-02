package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.model.User;
import com.serh.trackmoney.repository.UserRepository;
import com.serh.trackmoney.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findOneById(final Long id) {
        return ofNullable(userRepository.getOne(id));
    }

    @Override
    public User update(final User entity) {
        return null;
    }

    @Override
    public User save(final User entity) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
