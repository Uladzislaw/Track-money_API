package com.serh.trackmoney.dto;

import com.serh.trackmoney.annotation.PutNullable;
import com.serh.trackmoney.model.AccountState;
import com.serh.trackmoney.model.Role;
import com.serh.trackmoney.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Convertable<User, UserDto>, EntityDto {

    private String email;

    private String password;
    @PutNullable
    private String matchingPassword;

    private Role role;
    private AccountState state;


    @Override
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
