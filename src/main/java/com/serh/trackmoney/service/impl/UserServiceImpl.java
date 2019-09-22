package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.AccountState;
import com.serh.trackmoney.model.Role;
import com.serh.trackmoney.model.User;
import com.serh.trackmoney.repository.CategoryRepository;
import com.serh.trackmoney.repository.UserRepository;
import com.serh.trackmoney.service.UserService;
import com.serh.trackmoney.util.processor.EmailValidatorProcessor;
import com.serh.trackmoney.util.processor.RegistrationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.serh.trackmoney.util.NullableFieldInterceptor.interceptNullFieldAndThrow;
import static com.serh.trackmoney.util.PageRequestCreator.createPageRequest;
import static com.serh.trackmoney.util.PaginationQueryErrorInterceptor.interceptIncorrectDataAndThrow;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RegistrationProcessor registrationProcessor;
    private final CategoryRepository categoryRepository;
    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findOneById(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(final User entity) throws UserAlreadyExistsException {
        if (!userRepository.existsByEmail(entity.getEmail())) {
            return saveUser(entity);
        }
        throw new UserAlreadyExistsException("User with this email exists.");
    }

    private User saveUser(final User user) {
        user.setRole(Role.USER);
        user.setState(AccountState.ACTIVE);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCategories(categoryRepository.findAllDefault());
        return userRepository.save(user);
    }

    @Override
    public User save(final UserDto userDto) throws UserAlreadyExistsException {
        registrationProcessor.validateOrElseThrow(userDto);
        return save(userDto.toEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(final int page, final int size, final String sort) {
        interceptIncorrectDataAndThrow(page, size, sort);
        return userRepository.findAll(createPageRequest(page - 1, size, sort));
    }

    @Override
    public Page<User> findByCategory(final String name, int page, int size, final String sort) {
        return userRepository
                .findUsersByCategories(categoryRepository.findByName(name),
                        createPageRequest(page, size, sort));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findOneByEmail(final String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(final User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(final Long id, final UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        interceptNullFieldAndThrow(userDto);
        user.setEmail(userDto.getEmail());
        user.setState(user.getState());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(userDto.getRole());
        return update(user);
    }

    @Override
    public User updateByNonNullFields(final Long id, final UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        if (nonNull(userDto.getEmail())) {
            new EmailValidatorProcessor().validateOrElseThrow(userDto);
            user.setEmail(userDto.getEmail());
        }
        if (nonNull(userDto.getPassword())) {
            user.setPassword(encoder.encode(userDto.getPassword()));
        }
        if (nonNull(userDto.getRole())) {
            user.setRole(userDto.getRole());
        }
        if (nonNull(userDto.getState())) {
            user.setState(userDto.getState());
        }
        return update(user);
    }


    @Override
    public User makeInactive(final Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this id not found"));
        user.setState(AccountState.INACTIVE);
        return userRepository.save(user);
    }
}
