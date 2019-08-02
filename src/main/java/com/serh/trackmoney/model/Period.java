package com.serh.trackmoney.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "period")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Period extends Entity {

    @NotBlank
    private LocalDate beginning;

    @NotBlank
    private LocalDate end;
}
