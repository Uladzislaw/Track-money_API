package com.serh.trackmoney.dto;

import com.serh.trackmoney.annotation.PutNullable;
import com.serh.trackmoney.model.AccountState;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.Role;
import com.serh.trackmoney.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @PutNullable
    private List<Category> categories;


    @Override
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
