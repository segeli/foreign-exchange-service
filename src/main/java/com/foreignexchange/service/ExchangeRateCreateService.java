package com.foreignexchange.service;

import com.foreignexchange.entity.ExchangeRate;
import com.foreignexchange.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateCreateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public void bulkCreate(List<ExchangeRate> exchangeRates) {
        exchangeRateRepository.saveAll(exchangeRates);
    }
}
