package com.currency.exchange.api;

import com.currency.exchange.service.implementation.ConverterConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/converter/configuration")
public class CurrencyConverterConfigController {
    private final ConverterConfigurationService configurationService;

    /**
     * This endpoint sets up currency update interval in minutes for getting actual results.
     * @param interval          The update interval in minutes.
     */
    @PutMapping(value = "/interval/{interval}")
    public void setConverterExchangeRateUpdateInterval(@PathVariable(name = "interval") long interval) {
        configurationService.setRatesUpdateInterval(Duration.ofMinutes(interval));
    }

    /**
     * This endpoint retrieves currency update interval in minutes.
     * @return                  The update interval in minutes.
     */
    @GetMapping(value = "/interval")
    public long getCurrentIntervalInMinutes() {
        return configurationService.getCurrentUpdateInterval();
    }
}
