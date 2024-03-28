package com.currency.exchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrencyExchangeDto implements Serializable {

    /**
     * Defines the string value of currency code.
     */
    private String currencyCodeFrom;

    /**
     * The amount of provided currency far exchange rate providing.
     */
    private BigDecimal amountFrom;

    /**
     * Defines the string value of currency code.
     */
    private String currencyCodeTo;

    /**
     * The amount of provided currency far exchange rate providing.
     */
    private BigDecimal amountTo;

    /**
     * Represents the current currency exchange rates for provided currency;
     */
    private Map<String, BigDecimal> rates;
}
