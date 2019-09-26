package com.serh.trackmoney.dto;

import com.serh.trackmoney.model.Currency;
import com.serh.trackmoney.model.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestWrapper {
    @NotNull
    private Period period;
    @NotNull
    private Currency currency;
}
