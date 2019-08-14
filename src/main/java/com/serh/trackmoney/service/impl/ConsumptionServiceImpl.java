package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.repository.ConsumptionRepository;
import com.serh.trackmoney.repository.UserRepository;
import com.serh.trackmoney.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.serh.trackmoney.util.NullableFieldInterceptor.interceptNullFieldAndThrow;
import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<Consumption> findOneById(final Long id) {
        return consumptionRepository.findById(id);
    }

    @Override
    public Consumption update(final Consumption consumption) {
        return consumptionRepository.save(consumption);
    }

    @Override
    public Consumption update(final ConsumptionDto consumptionDto) {
        interceptNullFieldAndThrow(consumptionDto);
        Consumption userConsumption = consumptionRepository
                .findByAdditionDate(consumptionDto.getAdditionDate());
        userConsumption.setAmount(consumptionDto.getAmount());
        userConsumption.setCategory(consumptionDto.getCategory());
        userConsumption.setCurrency(consumptionDto.getCurrency());
        return update(userConsumption);
    }

    @Override
    public Consumption updateByNonNullField(final ConsumptionDto consumptionDto) {
        Consumption userConsumption = consumptionRepository
                .findByAdditionDate(consumptionDto.getAdditionDate());
        if (nonNull(consumptionDto.getAmount())) {
            userConsumption.setAmount(consumptionDto.getAmount());
        }
        if (nonNull(consumptionDto.getCurrency())) {
            userConsumption.setCurrency(consumptionDto.getCurrency());
        }
        if (nonNull(consumptionDto.getCategory())) {
            userConsumption.setCategory(consumptionDto.getCategory());
        }
        return update(userConsumption);
    }

    @Override
    public Consumption save(@Valid final Consumption consumption) {
        return consumptionRepository.save(consumption);
    }

    @Override
    public Consumption save(final ConsumptionDto consumptionDto,
                            final Long userId) {
        Consumption consumption = consumptionDto.toEntity();
        consumption.setUser(userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new));
        return consumption;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Consumption> findAll() {
        return consumptionRepository.findAll();
    }
}
