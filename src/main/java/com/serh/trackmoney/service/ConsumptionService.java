package com.serh.trackmoney.service;

import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.model.Consumption;

public interface ConsumptionService extends CrudService<Consumption> {
    Consumption update(ConsumptionDto consumptionDto);

    Consumption updateByNonNullField(ConsumptionDto consumptionDto);

    Consumption save(ConsumptionDto consumptionDto, Long userId);

    void delete(Long id);
}
