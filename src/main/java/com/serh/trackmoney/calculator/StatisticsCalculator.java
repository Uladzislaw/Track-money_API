package com.serh.trackmoney.calculator;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.CategoryType;
import com.serh.trackmoney.model.Consumption;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

@Component
public class StatisticsCalculator {

    public BigDecimal calcForCategory(final Category category, final List<Consumption> consumptions) {
        BigDecimal amount = ZERO;
        for (Consumption c : consumptions) {
            if (c.getCategory().getName().equals(category.getName())) {
                amount = add(c.getAmount(), amount);
            }
        }
        return amount;
    }

    public BigDecimal calcTotalByType(final List<Consumption> consumptions,
                                      final CategoryType type) {
        BigDecimal amount = ZERO;
        for (Consumption c : consumptions) {
            if (c.getCategory().getType().equals(type)) {
                amount = add(c.getAmount(), amount);
            }
        }
        return amount;
    }

    private BigDecimal add(final BigDecimal one, final BigDecimal two) {
        return one.add(two);
    }
}
