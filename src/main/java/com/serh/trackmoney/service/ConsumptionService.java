package com.serh.trackmoney.service;

import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.model.Consumption;
import org.springframework.data.domain.Page;

public interface ConsumptionService extends CrudService<Consumption> {
    Consumption update(Long id, ConsumptionDto consumptionDto);

    Consumption updateByNonNullField(Long id, ConsumptionDto consumptionDto);

    Consumption save(ConsumptionDto consumptionDto, Long userId);

    Page<Consumption> findAll(Integer page, Integer size, String sort);

    void delete(Long id);
}
