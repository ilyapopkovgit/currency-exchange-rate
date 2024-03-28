package com.currency.exchange.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "converter")
@EnableScheduling
public class ConverterConfig {

    /**
     * The key to access api for exchange rates.
     */
    private String exchangeRateApiKey;

    /**
     * Defines the host of the external API to get related exchange rate information.
     */
    private String exchangeRateApiHost;

    /**
     * The interval which defines frequency of updating rates.
     */
    private Duration rateUpdateInterval;

    /**
     * The interval which defines frequency of updating rates in case of failure of the original request.
     */
    private Duration rateUpdateRetryInterval;
}
