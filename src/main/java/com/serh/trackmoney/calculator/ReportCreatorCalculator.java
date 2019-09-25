package com.serh.trackmoney.calculator;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.model.Period;
import com.serh.trackmoney.model.Report;
import com.serh.trackmoney.model.User;
import com.serh.trackmoney.repository.ConsumptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.serh.trackmoney.model.CategoryType.CONSUMPTION;
import static com.serh.trackmoney.model.CategoryType.INCOME;
import static com.serh.trackmoney.model.Report.builder;

@RequiredArgsConstructor
@Component
public class ReportCreatorCalculator {

    private final ConsumptionRepository consumptionRepository;
    private final StatisticsCalculator calculator;

    public Report createDefaultReport(final Period period, final User user) {
        List<Consumption> consumptions
                = consumptionRepository.findByAdditionDateBetween(
                period.getBeginning(), period.getEnd());
        Map<Category, BigDecimal> expensesByCategory = new HashMap<>();
        user.getCategories().forEach(
                c -> expensesByCategory.put(c, calculator.calcForCategory(c, consumptions)));
        return builder()
                .expenseOnCategory(expensesByCategory)
                .period(period)
                .totalExpenses(calculator.calcTotalByType(consumptions, CONSUMPTION))
                .totalIncome(calculator.calcTotalByType(consumptions, INCOME))
                .build();
    }
}
