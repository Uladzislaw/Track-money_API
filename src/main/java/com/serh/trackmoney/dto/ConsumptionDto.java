package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionDto
        implements EntityDto, Convertable<Consumption, ConsumptionDto> {

    private BigDecimal amount;
    private LocalDate additionDate;
    private Category category;
    private Currency currency;

    @Override
    public Consumption toEntity() {
        return Consumption.builder()
                .amount(amount)
                .additionDate(LocalDate.now())
                .category(category)
                .currency(currency)
                .build();
    }

    public static ConsumptionDto of(final Consumption consumption) {
        return builder()
                .currency(consumption.getCurrency())
                .amount(consumption.getAmount())
                .additionDate(consumption.getAdditionDate())
                .category(consumption.getCategory())
                .build();
    }
}
