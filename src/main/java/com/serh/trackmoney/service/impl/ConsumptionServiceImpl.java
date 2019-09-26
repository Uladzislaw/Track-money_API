package com.serh.trackmoney.service.impl;

import com.serh.trackmoney.dto.ConsumptionDto;
import com.serh.trackmoney.exception.api.CategoryNotFoundException;
import com.serh.trackmoney.exception.api.ConsumptionNotFoundException;
import com.serh.trackmoney.exception.api.UserNotFoundException;
import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.Consumption;
import com.serh.trackmoney.model.User;
import com.serh.trackmoney.repository.CategoryRepository;
import com.serh.trackmoney.repository.ConsumptionRepository;
import com.serh.trackmoney.repository.CurrencyRepository;
import com.serh.trackmoney.repository.UserRepository;
import com.serh.trackmoney.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.serh.trackmoney.util.NullableFieldInterceptor.interceptNullFieldAndThrow;
import static com.serh.trackmoney.util.PageRequestCreator.createPageRequest;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CurrencyRepository currencyRepository;

    private final Supplier<ConsumptionNotFoundException> consumptionNotFoundException = () ->
            new ConsumptionNotFoundException("Consumption with this id not found");

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
    public Consumption update(final Long id, final ConsumptionDto consumptionDto) {
        interceptNullFieldAndThrow(consumptionDto);
        Consumption userConsumption = consumptionRepository
                .findById(id)
                .orElseThrow(consumptionNotFoundException);
        userConsumption.setAmount(consumptionDto.getAmount());
        userConsumption.setCategory(categoryRepository
                .findByName(consumptionDto.getCategory().getName()));
        userConsumption.setCurrency(currencyRepository
                .findByName(consumptionDto.getCurrency().getName()));
        return update(userConsumption);
    }

    @Override
    public Consumption updateByNonNullField(final Long id,
                                            final ConsumptionDto consumptionDto) {
        Consumption userConsumption = consumptionRepository
                .findById(id).orElseThrow(consumptionNotFoundException);
        if (nonNull(consumptionDto.getAmount())) {
            userConsumption.setAmount(consumptionDto.getAmount());
        }
        if (nonNull(consumptionDto.getCurrency())) {
            userConsumption.setCurrency(currencyRepository
                    .findByName(consumptionDto.getCurrency().getName()));
        }
        if (nonNull(consumptionDto.getCategory())) {
            userConsumption.setCategory(categoryRepository
                    .findByName(consumptionDto.getCategory().getName()));
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
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Category category
                = categoryRepository.findByName(consumptionDto.getCategory().getName());
        if (isNull(category)) {
            throw new CategoryNotFoundException("Category with this name does not exist");
        }
        Consumption consumption = Consumption.builder()
                .currency(currencyRepository.findByName(consumptionDto.getCurrency().getName()))
                .user(user)
                .category(category)
                .additionDate(LocalDate.now())
                .amount(consumptionDto.getAmount())
                .build();
        return save(consumption);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Consumption> findAll() {
        return consumptionRepository.findAll();
    }

    @Override
    public Page<Consumption> findAll(final Integer page,
                                     final Integer size,
                                     final String sort) {
        return consumptionRepository.findAll(createPageRequest(page, size, sort));
    }

    @Override
    public void delete(final Long id) {
        consumptionRepository.findById(id).ifPresent(this::delete);
    }

    private void delete(final Consumption consumption) {
        consumptionRepository.deleteById(consumption.getId());
    }
}
