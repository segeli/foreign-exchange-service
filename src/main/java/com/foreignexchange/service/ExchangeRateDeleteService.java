package com.foreignexchange.service;

import com.foreignexchange.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateDeleteService {

    private final ExchangeRateRepository exchangeRateRepository;

    public void deleteAll() {
        exchangeRateRepository.deleteAll();
    }
}
