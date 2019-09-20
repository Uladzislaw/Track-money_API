package com.serh.trackmoney.controller;

import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.exception.api.ConsumptionNotFoundException;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.service.CategoryService;
import com.serh.trackmoney.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/v1/consumptions")
@RequiredArgsConstructor
public class ConsumptionController {

    private final ConsumptionService consumptionService;
    private CategoryService categoryService;

    private Supplier<ConsumptionNotFoundException> consumptionNotFoundException
            = () -> new ConsumptionNotFoundException("Consumption not found.");
    private Supplier<CategoryNotFoundException> categoryNotFoundException
            = () -> new CategoryNotFoundException("Category not found.");

    @GetMapping
    public List<Resource<ConsumptionDto>> all() {
        return null;
    }

    @GetMapping(value = "/category/{categoryId}")
    public List<Consumption> getByCategory(@PathVariable final Long categoryId) {
        return categoryService.findOneById(categoryId)
                .orElseThrow(categoryNotFoundException)
                .getConsumption();
    }

    @GetMapping(value = "/{id}")
    public Consumption getOne(@PathVariable final Long id) {
        return consumptionService.findOneById(id)
                .orElseThrow(consumptionNotFoundException);
    }
}
