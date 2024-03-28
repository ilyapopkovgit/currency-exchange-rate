package com.currency.exchange.service.implementation;

import com.currency.exchange.config.ConverterConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConverterConfigurationService {
    private final ConverterConfig converterConfig;

    public void setRatesUpdateInterval(Duration interval) {
        converterConfig.setRateUpdateInterval(interval);
        log.info("The currency exchange rates update interval is set to %s minutes".formatted(interval.toMinutes()));
    }

    public long getCurrentUpdateInterval() {
        return converterConfig.getRateUpdateInterval().toMinutes();
    }
}
