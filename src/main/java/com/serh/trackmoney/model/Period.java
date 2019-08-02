package com.serh.trackmoney.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "period")
public class Period extends Entity {

    @NotBlank
    private LocalDate beginning;

    @NotBlank
    private LocalDate end;
}
