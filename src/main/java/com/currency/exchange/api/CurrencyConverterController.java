package com.currency.exchange.api;

import com.currency.exchange.dto.CurrencyExchangeDto;
import com.currency.exchange.service.implementation.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/converter")
@RequiredArgsConstructor
public class CurrencyConverterController {
    private final CurrencyConverterService converterService;

    /**
     * This endpoint retrieves currency exchange rate for provided currency code.
     * @param baseCode      The base code of the currency.
     * @return              The dto with data related to currency exchange.
     */
    @GetMapping("/baseCurrencyRate/{baseCode}")
    @Cacheable(value = "rate", key = "#baseCode")
    public CurrencyExchangeDto getRatesForCurrency(@PathVariable String baseCode) {
        return converterService.getByCodeForCurrency(baseCode);
    }

    /**
     * This endpoint retrieves conversion rate from one currency with provided amount into another currency.
     * @param baseCode      The base code of the currency to convert from.
     * @param amount        The amount to convert.
     * @param currencyTo    The code of the currency to convert to.
     * @return              The dto with data related to currency exchange.
     */
    @GetMapping("/rate/{baseCode}/{amount}/{currencyTo}")
    public CurrencyExchangeDto getRate(@PathVariable String baseCode, @PathVariable BigDecimal amount, @PathVariable String currencyTo) {
        return converterService.getExchangeRate(baseCode, amount, currencyTo);
    }
}
