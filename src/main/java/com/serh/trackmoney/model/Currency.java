package com.serh.trackmoney.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "currency")
public class Currency extends Entity {

    @NotBlank
    @Column(unique = true)
    private String name;
}
