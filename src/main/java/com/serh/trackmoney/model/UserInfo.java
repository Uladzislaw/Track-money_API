package com.serh.trackmoney.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "user_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends Entity {

    @OneToOne
    @JoinColumn(name = "u_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
    @OneToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;
}
