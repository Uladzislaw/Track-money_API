package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.AccountState;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.model.Report;
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
    private String matchingPassword;

    private Role role;
    private AccountState state;

    private List<Consumption> consumptions;
    private List<Report> reports;


    @Override
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
