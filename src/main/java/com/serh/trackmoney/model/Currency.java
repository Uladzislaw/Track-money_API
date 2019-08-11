package com.serh.trackmoney.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "currency")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends Entity {

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private CurrencySign name;

    @OneToMany
    private List<Consumption> consumptions;
}
