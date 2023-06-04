package com.foreignexchange.service;

import com.foreignexchange.entity.ExchangeRate;
import com.foreignexchange.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ExchangeRateReadServiceTest {

    @InjectMocks
    private ExchangeRateReadService exchangeRateReadService;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Test
    void shouldFindSelfIfCurrencyPairsAreEqual() {
        //when
        Optional<BigDecimal> actual = exchangeRateReadService.find("USD", "USD");

        //then
        assertThat(actual).isNotEmpty().get().isEqualTo(BigDecimal.ONE);
    }

    @Test
    void shouldFindEmptyIfAnyCurrencyCouldNotBeFound() {
        //given
        when(exchangeRateRepository.findByTargetIn(List.of("BTC", "USD")))
                .thenReturn(Collections.singletonList(new ExchangeRate()));

        //when
        Optional<BigDecimal> actual = exchangeRateReadService.find("BTC", "USD");

        //then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldFind() {
        //given
        ExchangeRate euroCurrency = ExchangeRate.builder()
                .source("EUR")
                .sourceAmount(BigDecimal.ONE)
                .target("EUR")
                .targetAmount(BigDecimal.ONE)
                .build();
        ExchangeRate tryCurrency = ExchangeRate.builder()
                .source("EUR")
                .sourceAmount(BigDecimal.ONE)
                .target("TRY")
                .targetAmount(BigDecimal.valueOf(21.08d))
                .build();
        when(exchangeRateRepository.findByTargetIn(anyList()))
                .thenReturn(List.of(euroCurrency, tryCurrency));

        //when
        Optional<BigDecimal> actual = exchangeRateReadService.find("EUR", "TRY");

        //then
        assertThat(actual).isNotEmpty().get().isEqualTo(BigDecimal.valueOf(21.08d));
    }
}