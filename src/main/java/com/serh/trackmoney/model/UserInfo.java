package com.serh.trackmoney.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "user_info")
public class UserInfo extends Entity {

    @OneToOne
    @JoinColumn(name = "u_id")
    @ToString.Exclude
    private User user;
    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
