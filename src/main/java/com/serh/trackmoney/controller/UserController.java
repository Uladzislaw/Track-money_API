package com.serh.trackmoney.controller;

import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.dto.annotation.EmailValidator;
import com.serh.trackmoney.exception.api.EmailNotValidException;
import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.User;
import com.serh.trackmoney.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private Supplier<UserNotFoundException> userNotFoundException
            = () -> new UserNotFoundException("User with this email not found.");

    @GetMapping(value = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable final String email) {
        if (!email.matches(EmailValidator.getEMAIL_PATTERN())) {
            throw new EmailNotValidException("Email is incorrect.");
        }
        return userService
                .findOneByEmail(email)
                .orElseThrow(userNotFoundException);
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody @Valid final UserDto userDto)
            throws UserAlreadyExistsException {
        User newUser = userService.save(userDto.toEntity());
        return ResponseEntity
                .created(URI.create("/api/v1/users/" + newUser.getId()))
                .build();
    }
}
