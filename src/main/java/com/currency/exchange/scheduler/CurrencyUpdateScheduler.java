package com.currency.exchange.scheduler;

import com.currency.exchange.service.implementation.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableRetry
public class CurrencyUpdateScheduler {
    private final CurrencyConverterService converterService;
    private final String BASE_CURRENCY = "USD";

    @Scheduled(fixedDelayString = "#{converterConfig.rateUpdateInterval}")
    @Retryable(backoff = @Backoff(delayExpression = "#{@converterConfig.rateUpdateRetryInterval.toMillis()}"))
    public void updateRatesForBaseCurrency() {
        converterService.updateRatesForBaseCurrency(BASE_CURRENCY);
    }

}
