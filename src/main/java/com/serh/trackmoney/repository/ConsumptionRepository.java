package com.serh.trackmoney.repository;

import com.serh.trackmoney.model.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
    Consumption findByAdditionDate(final LocalDate additionDate);
}
