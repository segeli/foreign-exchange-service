package com.foreignexchange.service;

import com.foreignexchange.exception.CurrencyPairNotFoundException;
import com.foreignexchange.model.ConversionConvertRequest;
import com.foreignexchange.model.ConversionConvertResponse;
import com.foreignexchange.model.ConversionCreateRequest;
import com.foreignexchange.model.ConversionCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ConversionCapabilityService {

    private final ExchangeRateReadService exchangeRateReadService;
    private final ConversionCreateService conversionCreateService;

    public ConversionConvertResponse convert(ConversionConvertRequest request) throws CurrencyPairNotFoundException {
        BigDecimal targetAmount = convert(request.getSourceAmount(), request.getSourceCurrency(), request.getTargetCurrency());

        ConversionCreateResponse conversionCreateResponse = createAndGetConversion(request, targetAmount);
        return ConversionConvertResponse.builder()
                .transactionId(conversionCreateResponse.getId())
                .targetAmount(targetAmount)
                .build();
    }

    private BigDecimal convert(BigDecimal sourceAmount,
                               String sourceCurrency,
                               String targetCurrency
    ) throws CurrencyPairNotFoundException {
        BigDecimal exchangeRate = exchangeRateReadService.find(sourceCurrency, targetCurrency)
                .orElseThrow(() -> {
                            String currencyPair = sourceCurrency.concat("-").concat(targetCurrency);
                            return new CurrencyPairNotFoundException(currencyPair);
                        }
                );

        return sourceAmount.multiply(exchangeRate);
    }

    private ConversionCreateResponse createAndGetConversion(ConversionConvertRequest request,
                                                            BigDecimal targetAmount
    ) {
        ConversionCreateRequest conversionCreateRequest = ConversionCreateRequest.builder()
                .sourceCurrency(request.getSourceCurrency())
                .sourceAmount(request.getSourceAmount())
                .targetCurrency(request.getTargetCurrency())
                .targetAmount(targetAmount)
                .build();
        return conversionCreateService.create(conversionCreateRequest);
    }
}
