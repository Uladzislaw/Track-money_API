package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.model.Role;
import com.serh.trackmoney.model.User;
import com.serh.trackmoney.repository.UserRepository;
import com.serh.trackmoney.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder encoder;

    @Override
    public Optional<User> findOneById(final Long id) {
        return ofNullable(userRepository.getOne(id));
    }

    @Override
    public User update(final User entity) {
        return null;
    }

    @Override
    public User save(final User entity) throws UserAlreadyExistsException {
        if (!userRepository.existsByEmail(entity.getEmail())) {
            return saveUser(entity);
        }
        throw new UserAlreadyExistsException("User with this email exists.");
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private User saveUser(final User user) {
        user.setRole(Role.USER);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
