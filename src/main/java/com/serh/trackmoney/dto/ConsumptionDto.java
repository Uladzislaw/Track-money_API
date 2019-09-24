package com.serh.trackmoney.dto;

import com.serh.trackmoney.annotation.PutNullable;
import com.serh.trackmoney.model.Consumption;
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
    @PutNullable
    private LocalDate additionDate;
    private CategoryDto category;
    private CurrencyDto currency;

    @Override
    public Consumption toEntity() {
        throw new UnsupportedOperationException(
                "Consumption cannot be full converted to entity");
    }

    public static ConsumptionDto of(final Consumption consumption) {
        return builder()
                .currency(consumption.getCurrency().toDto())
                .amount(consumption.getAmount())
                .additionDate(consumption.getAdditionDate())
                .category(consumption.getCategory().toDto())
                .build();
    }
}
