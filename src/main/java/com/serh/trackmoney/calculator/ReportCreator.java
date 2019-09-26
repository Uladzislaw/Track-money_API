package com.serh.trackmoney.calculator;

import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.model.Currency;
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
import static java.util.Collections.singletonList;

@RequiredArgsConstructor
@Component
public class ReportCreator {

    private final ConsumptionRepository consumptionRepository;
    private final StatisticsCalculator calculator;

    public Report createDefaultReport(final Period period,
                                      final User user,
                                      final Currency currency) {
        List<Consumption> consumptions = getConsumptions(period, currency);
        Map<Category, BigDecimal> expensesByCategory = new HashMap<>();
        user.getCategories().forEach(
                c -> expensesByCategory.put(c, calculator.calcForCategory(c, consumptions)));
        expensesByCategory.forEach((key, value) -> key.getConsumption().clear());
        return Report.builder()
                .expenseOnCategory(expensesByCategory)
                .period(period)
                .totalExpenses(calculator.calcTotalByType(consumptions, CONSUMPTION))
                .totalIncome(calculator.calcTotalByType(consumptions, INCOME))
                .build();
    }

    public Report createReportForCategory(final Period period,
                                          final User user,
                                          final Currency currency,
                                          final String categoryName) {
        User u = User.builder()
                .categories(singletonList(user.getCategories().stream()
                        .filter(c -> c.getName().equals(categoryName))
                        .findFirst()
                        .orElseThrow(CategoryNotFoundException::new)))
                .build();
        return createDefaultReport(period, u, currency);
    }

    private List<Consumption> getConsumptions(Period period, Currency currency) {
        return consumptionRepository.findByAdditionDateBetweenAndCurrency_Name(
                period.getBeginning(), period.getEnd(), currency.getName());
    }
}
