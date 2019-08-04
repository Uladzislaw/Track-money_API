package com.serh.trackmoney.dto;

import com.serh.trackmoney.dto.annotation.PasswordMatches;
import com.serh.trackmoney.dto.annotation.ValidEmail;
import com.serh.trackmoney.model.Role;
import com.serh.trackmoney.model.User;
import lombok.Data;
import lombok.NonNull;

@PasswordMatches
@Data
public class UserDto implements EntityDto, Convertable<User> {

    @NonNull
    @ValidEmail
    private String email;

    @NonNull
    private String password;
    private String matchingPassword;

    private Role role;

    @Override
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }

    @Override
    public void convert(final User entity) {
        this.setEmail(entity.getEmail());
        this.setRole(entity.getRole());
    }
}
