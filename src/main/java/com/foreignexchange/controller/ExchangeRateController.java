package com.foreignexchange.controller;

import com.foreignexchange.service.ExchangeRateReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(ExchangeRateController.ENDPOINT)
@RequiredArgsConstructor
public class ExchangeRateController {

    public static final String ENDPOINT = "exchange-rates";

    private final ExchangeRateReadService exchangeRateReadService;

    @GetMapping("/{currencyPair}")
    public double getExchangeRate(@PathVariable("currencyPair") String currencyPair) {
        String[] currencyPairs = currencyPair.strip().split("-");
        String source = currencyPairs[0];
        String target = currencyPairs[1];

        return exchangeRateReadService.find(source, target).orElse(BigDecimal.ZERO).doubleValue();
    }
}
