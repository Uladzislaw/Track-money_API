package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.Role;
import com.serh.trackmoney.model.User;
import com.serh.trackmoney.repository.UserRepository;
import com.serh.trackmoney.service.UserService;
import com.serh.trackmoney.util.processor.RegistrationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    private final RegistrationProcessor registrationProcessor;
    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    @Override
    public Optional<User> findOneById(final Long id) {
        return ofNullable(userRepository.getOne(id));
    }

    @Override
    public User update(final User entity) {
        return userRepository.save(entity);
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

    @Override
    public User save(final UserDto userDto) throws UserAlreadyExistsException {
        registrationProcessor.validateOrElseThrow(userDto);
        return save(userDto.toEntity());
    }

    @Override
    public List<User> findAll(final int page, final int size, final String sort) {
        return null;
    }


    @Override
    public Optional<User> findOneByEmail(final String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User update(final Long id, final UserDto userDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(userDto.getRole());
        return update(user);
    }
}
