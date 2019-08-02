package com.serh.trackmoney.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report extends Entity {

    private BigDecimal income;

    private BigDecimal totalExpenses;

    private Map<Category, BigDecimal> expenseOnCategory;

    private Period period;
}
