package com.currency.exchange.service.implementation;

import com.currency.exchange.config.RestTemplateResponseErrorHandler;
import com.currency.exchange.dto.CurrencyExchangeDto;
import com.currency.exchange.model.Rate;
import com.currency.exchange.service.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyConverterService implements CurrencyConverter {

    @Value("${converter.exchange-rate-api-host}")
    private String exchangeRateApiHost;

    @Value("${converter.exchange-rate-api-key}")
    private String exchangeRateApiKey;

    private RestTemplate restTemplate;

    @Autowired
    public CurrencyConverterService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    public CurrencyExchangeDto getExchangeRate(String currencyFrom, BigDecimal amount, String currencyTo) {
        return CurrencyExchangeDto.builder()
                .currencyCodeFrom(currencyFrom)
                .amountFrom(amount)
                .currencyCodeTo(currencyTo)
                .amountTo(getRateForCurrency(currencyFrom, amount, currencyTo))
                .build();
    }

    private BigDecimal getRateForCurrency(String currencyFrom, BigDecimal amount, String currencyTo) {
        String uri = exchangeRateApiHost + exchangeRateApiKey + "/latest/" + currencyFrom;
        return Objects.requireNonNull(restTemplate.getForObject(uri, Rate.class)).getConversionRates().get(currencyTo).multiply(amount);
    }

    @Override
    public CurrencyExchangeDto getByCodeForCurrency(String currencyCode) {
        String uri = exchangeRateApiHost + exchangeRateApiKey + "/latest/" + currencyCode;
        Map<String, BigDecimal> rates = Objects.requireNonNull(restTemplate.getForObject(uri, Rate.class)).getConversionRates();
        return CurrencyExchangeDto.builder()
                .currencyCodeFrom(currencyCode)
                .rates(rates)
                .build();
    }

    @Override
    @CachePut(cacheNames = "rate", key = "#baseCode")
    public Rate updateRatesForBaseCurrency(String baseCode) {
        String uri = exchangeRateApiHost + exchangeRateApiKey + "/latest/" + baseCode;
        Rate baseRate = new Rate();
        baseRate.setBaseCode(baseCode);
        baseRate.setConversionRates(Objects.requireNonNull(restTemplate.getForObject(uri, Rate.class)).getConversionRates());
        log.info("Rates updated for base currency %s ".formatted(baseCode).toUpperCase());
        System.out.println();
        return baseRate;
    }
}
