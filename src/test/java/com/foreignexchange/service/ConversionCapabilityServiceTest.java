package com.foreignexchange.service;

import com.foreignexchange.exception.CurrencyPairNotFoundException;
import com.foreignexchange.model.ConversionConvertRequest;
import com.foreignexchange.model.ConversionConvertResponse;
import com.foreignexchange.model.ConversionCreateResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ConversionCapabilityServiceTest {

    @InjectMocks
    private ConversionCapabilityService conversionCapabilityService;

    @Mock
    private ExchangeRateReadService exchangeRateReadService;

    @Mock
    private ConversionCreateService conversionCreateService;

    @Test
    @SneakyThrows
    void shouldConvert() {
        //given
        BigDecimal sourceAmount = BigDecimal.valueOf(200d);
        ConversionConvertRequest request = ConversionConvertRequest.builder()
                .sourceCurrency("USD")
                .sourceAmount(sourceAmount)
                .targetCurrency("TRY")
                .build();

        BigDecimal exchangeRate = BigDecimal.valueOf(21.07d);
        BigDecimal targetAmount = sourceAmount.multiply(exchangeRate);
        UUID uuid = UUID.randomUUID();
        ConversionCreateResponse conversionCreateResponse = ConversionCreateResponse.builder()
                .id(uuid.toString())
                .sourceCurrency(request.getSourceCurrency())
                .sourceAmount(request.getSourceAmount())
                .targetCurrency(request.getTargetCurrency())
                .targetAmount(targetAmount)
                .build();

        when(exchangeRateReadService.find(eq("USD"), eq("TRY")))
                .thenReturn(Optional.of(exchangeRate));
        when(conversionCreateService.create(any()))
                .thenReturn(conversionCreateResponse);

        //when
        ConversionConvertResponse actual = conversionCapabilityService.convert(request);

        //then
        assertThat(actual)
                .returns(uuid.toString(), ConversionConvertResponse::getTransactionId)
                .returns(targetAmount, ConversionConvertResponse::getTargetAmount);
    }

    @Test
    @SneakyThrows
    void shouldNotConvert() {
        //given
        ConversionConvertRequest request = ConversionConvertRequest.builder()
                .sourceCurrency("USD")
                .sourceAmount(BigDecimal.valueOf(200d))
                .targetCurrency("TRY")
                .build();

        when(exchangeRateReadService.find(anyString(), anyString()))
                .thenReturn(Optional.empty());

        //when
        Assertions.assertThrows(CurrencyPairNotFoundException.class, () -> {
            conversionCapabilityService.convert(request);
        });
    }
}