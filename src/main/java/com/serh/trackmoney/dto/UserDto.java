package com.serh.trackmoney.dto;

import com.serh.trackmoney.dto.annotation.PasswordMatches;
import com.serh.trackmoney.dto.annotation.ValidEmail;
import com.serh.trackmoney.model.Currency;
import com.serh.trackmoney.model.Role;
import com.serh.trackmoney.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@PasswordMatches
@Data
@NoArgsConstructor
@Builder
public class UserDto implements Convertable<User, UserDto>, EntityDto {

    @NonNull
    @ValidEmail
    private String email;

    @NonNull
    private String password;
    private String matchingPassword;

    private Role role;
    private Currency currency;


    @Override
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
