package com.foreignexchange.service;

import com.foreignexchange.entity.ExchangeRate;
import com.foreignexchange.exception.InvalidFormatException;
import com.foreignexchange.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRateReadService {

    private final ExchangeRateRepository exchangeRateRepository;

    public Optional<BigDecimal> find(String sourceCurrency, String targetCurrency) {
        if (sourceCurrency.equals(targetCurrency)) {
            return Optional.of(BigDecimal.ONE);
        }

        List<ExchangeRate> exchangeRates = exchangeRateRepository.findByTargetIn(List.of(sourceCurrency, targetCurrency));
        if (exchangeRates.size() != 2) {
            return Optional.empty();
        }

        Map<String, BigDecimal> pairMap = exchangeRates.stream()
                .collect(Collectors.toMap(ExchangeRate::getTarget, ExchangeRate::getTargetAmount));
        return Optional.of(pairMap.get(targetCurrency).divide(pairMap.get(sourceCurrency), RoundingMode.HALF_DOWN));
    }

    public double getExchangeRate(String currencyPair) throws InvalidFormatException {
        if (!isValidCurrencyPairFormat(currencyPair)) {
            throw new InvalidFormatException("Invalid currency pair format");
        }

        String[] currencyPairs = currencyPair.strip().split("-");
        String source = currencyPairs[0];
        String target = currencyPairs[1];

        return find(source, target).orElse(BigDecimal.ZERO).doubleValue();
    }

    private boolean isValidCurrencyPairFormat(String currencyPair) {
        return currencyPair.matches("^[A-Z]{3}-[A-Z]{3}$");
    }
}
