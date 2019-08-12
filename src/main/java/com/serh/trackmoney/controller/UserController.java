package com.serh.trackmoney.controller;

import com.serh.trackmoney.dto.UserDto;
import com.serh.trackmoney.exception.api.UserAlreadyExistsException;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.User;
import com.serh.trackmoney.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Supplier;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private Supplier<UserNotFoundException> userNotFoundException
            = () -> new UserNotFoundException("User with this email not found.");
    private static final String DEFAULT_PAGE_SIZE = "10";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDto> all(@RequestParam(value = "page") final int page,
                          @RequestParam(value = "size",
                                     defaultValue = DEFAULT_PAGE_SIZE) final int size,
                          @RequestParam(value = "sort",
                                     defaultValue = "id,asc") final String sort) {
        return userService.findAll(page, size, sort).map(User::toDto);
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody final UserDto userDto,
                                              @PathVariable final Long id) {
        return ResponseEntity.ok(userService.update(id, userDto).toDto());
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable final Long id) {
        userService.makeInactive(id);
    }
}
