package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Currency;
import com.serh.trackmoney.model.CurrencySign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto implements EntityDto, Convertable<Currency, CurrencyDto> {

    private CurrencySign name;

    @Override
    public Currency toEntity() {
        return Currency.builder()
                .name(name)
                .build();
    }
}
