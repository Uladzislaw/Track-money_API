package com.serh.trackmoney.controller;

import com.serh.trackmoney.controller.helper.RelatedLinkCreatorHelper;
import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.exception.api.ConsumptionNotFoundException;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

import static com.serh.trackmoney.dto.ConsumptionDto.of;

@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class ConsumptionController {

    private final ConsumptionService consumptionService;
    private final RelatedLinkCreatorHelper linkCreator;

    private Supplier<ConsumptionNotFoundException> consumptionNotFoundException
            = () -> new ConsumptionNotFoundException("Consumption not found.");

    @GetMapping
    public List<Resource<ConsumptionDto>> all() {
        return null;
    }

    @GetMapping(value = "/{id}")
    public Resource<ConsumptionDto> getOne(@PathVariable final Long id) {
        Consumption consumption = consumptionService.findOneById(id)
                .orElseThrow(consumptionNotFoundException);
        return addResource(consumption);
    }

    private Resource<ConsumptionDto> addResource(final Consumption consumption) {
        return new Resource<>(of(consumption),
                linkCreator.createSimpleLinkListForAllConsumptions(consumption));
    }
}
