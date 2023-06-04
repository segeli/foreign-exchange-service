package com.foreignexchange.service;

import com.foreignexchange.client.ForeignExchangeClient;
import com.foreignexchange.client.model.ExchangeRateResponse;
import com.foreignexchange.entity.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateCapabilityService {

    private final ForeignExchangeClient foreignExchangeClient;
    private final ExchangeRateCreateService exchangeRateCreateService;
    private final ExchangeRateDeleteService exchangeRateDeleteService;

    public void deleteAllAndBulkCreate() {
        exchangeRateDeleteService.deleteAll();

        ExchangeRateResponse exchangeRateResponse = foreignExchangeClient.getExchangeRates();

        final String source = exchangeRateResponse.base();
        List<ExchangeRate> exchangeRates = exchangeRateResponse.rates().entrySet()
                .stream().map(entry -> {
                    String target = entry.getKey();
                    Double targetAmount = entry.getValue();

                    return ExchangeRate.builder()
                            .source(source)
                            .sourceAmount(BigDecimal.ONE)
                            .target(target)
                            .targetAmount(BigDecimal.valueOf(targetAmount))
                            .build();
                }).toList();

        exchangeRateCreateService.bulkCreate(exchangeRates);
    }
}
