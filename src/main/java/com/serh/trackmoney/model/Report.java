package com.serh.trackmoney.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Report extends Entity {

    private BigDecimal income;

    private BigDecimal totalExpenses;

    private Map<Category, BigDecimal> expenseOnCategory;

    private Period period;
}
