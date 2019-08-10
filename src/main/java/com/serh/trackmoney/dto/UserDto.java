package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Currency;
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
