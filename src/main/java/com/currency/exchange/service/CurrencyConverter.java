package com.currency.exchange.service;

import com.currency.exchange.dto.CurrencyExchangeDto;
import com.currency.exchange.model.Rate;

import java.math.BigDecimal;

public interface CurrencyConverter {
    CurrencyExchangeDto getExchangeRate(String currencyFrom, BigDecimal amount, String currencyTo);

    CurrencyExchangeDto getByCodeForCurrency(String currencyCode);

    Rate updateRatesForBaseCurrency(String baseCode);
}
