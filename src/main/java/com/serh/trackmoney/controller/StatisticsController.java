package com.serh.trackmoney.controller;

import com.serh.trackmoney.calculator.ReportCreator;
import com.serh.trackmoney.dto.ReportRequestWrapper;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.Report;
import com.serh.trackmoney.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final ReportCreator creator;
    private final UserService userService;

    @GetMapping("/{userId}")
    public Report getStatisticsForUser(@PathVariable final Long userId,
                                       @RequestBody @Valid final ReportRequestWrapper wrapper) {
        return creator.createDefaultReport(wrapper.getPeriod(), userService.findOneById(userId)
                .orElseThrow(UserNotFoundException::new), wrapper.getCurrency());
    }
}
