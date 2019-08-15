package com.serh.trackmoney.repository;

import com.serh.trackmoney.model.Currency;
import com.serh.trackmoney.model.CurrencySign;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Currency findByName(CurrencySign name);
}
