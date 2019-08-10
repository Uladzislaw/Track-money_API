package com.serh.trackmoney.controller;

import com.serh.trackmoney.dto.UserDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private Supplier<UserNotFoundException> userNotFoundException
            = () -> new UserNotFoundException("User with this email not found.");

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> all(@RequestParam(value = "page") int page,
                             @RequestParam(value = "size") int size,
                             @RequestParam(value = "sort") String sort) {
        return userService.findAll(page, size, sort)
                .stream()
                .map(User::toDto)
                .collect(toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable final Long id) {
        return userService
                .findOneById(id)
                .orElseThrow(userNotFoundException)
                .toDto();
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody final UserDto userDto)
            throws UserAlreadyExistsException {
        User newUser = userService.save(userDto);
        return created(URI.create("/api/v1/users/" + newUser.getId()))
                .build();
    }
}
