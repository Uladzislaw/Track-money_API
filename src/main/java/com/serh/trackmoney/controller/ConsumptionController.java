package com.serh.trackmoney.controller;

import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.exception.api.ConsumptionNotFoundException;
import com.serh.trackmoney.service.CategoryService;
import com.serh.trackmoney.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

import static com.serh.trackmoney.dto.ConsumptionDto.of;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/consumptions")
@RequiredArgsConstructor
public class ConsumptionController {

    private final ConsumptionService consumptionService;
    private final CategoryService categoryService;

    private Supplier<ConsumptionNotFoundException> consumptionNotFoundException
            = () -> new ConsumptionNotFoundException("Consumption not found.");
    private Supplier<CategoryNotFoundException> categoryNotFoundException
            = () -> new CategoryNotFoundException("Category not found.");

    @GetMapping
    public List<ConsumptionDto> all() {
        return null;
    }

    @GetMapping(value = "/category/{categoryId}")
    public List<ConsumptionDto> getByCategory(@PathVariable final Long categoryId) {
        return categoryService.findOneById(categoryId)
                .orElseThrow(categoryNotFoundException)
                .getConsumption().stream()
                .map(ConsumptionDto::of)
                .collect(toList());
    }

    @GetMapping(value = "/{id}")
    public ConsumptionDto getOne(@PathVariable final Long id) {
        return of(consumptionService.findOneById(id)
                .orElseThrow(consumptionNotFoundException));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        consumptionService.delete(id);
    }
}
