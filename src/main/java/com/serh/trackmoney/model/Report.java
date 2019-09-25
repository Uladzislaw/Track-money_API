package com.serh.trackmoney.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private Map<Category, BigDecimal> expenseOnCategory;
    private Period period;
}
