package com.serh.trackmoney.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serh.trackmoney.dto.Convertable;
import com.serh.trackmoney.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends Entity implements Convertable<User, UserDto> {

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private UserInfo info;

    @Override
    public UserDto toDto() {
        return UserDto.builder()
                .email(email)
                .role(role)
                .currency(Currency.builder()
                        .name(info.getCurrency().getName())
                        .build())
                .build();
    }
}
