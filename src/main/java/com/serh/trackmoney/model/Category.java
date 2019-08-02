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
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Entity {

    @NotBlank
    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private CategoryType type;

    @Column(name = "total_expenses")
    private BigDecimal totalExpenses;

    @OneToMany
    private List<Consumption> consumption;
}
