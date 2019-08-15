package com.serh.trackmoney.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serh.trackmoney.dto.Convertable;
import com.serh.trackmoney.dto.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "currency")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends Entity implements Convertable<Currency, CurrencyDto> {

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private CurrencySign name;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
    @ToString.Exclude
    private List<Consumption> consumptions;

    @Override
    public CurrencyDto toDto() {
        return CurrencyDto.builder()
                .name(name)
                .build();
    }
}
