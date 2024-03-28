package com.currency.exchange.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate implements Serializable {

    @JsonAlias("base_code")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String baseCode;

    @JsonAlias("conversion_rates")
    private Map<String, BigDecimal> conversionRates;
}
