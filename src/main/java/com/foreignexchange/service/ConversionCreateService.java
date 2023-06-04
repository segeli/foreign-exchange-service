package com.foreignexchange.service;

import com.foreignexchange.entity.Conversion;
import com.foreignexchange.model.ConversionCreateRequest;
import com.foreignexchange.model.ConversionCreateResponse;
import com.foreignexchange.repository.ConversionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversionCreateService {

    private final ConversionRepository conversionRepository;

    public ConversionCreateResponse create(ConversionCreateRequest request) {
        Conversion conversion = Conversion.builder()
                .sourceCurrency(request.getSourceCurrency())
                .sourceAmount(request.getSourceAmount())
                .targetCurrency(request.getTargetCurrency())
                .targetAmount(request.getTargetAmount())
                .build();
        conversion = conversionRepository.save(conversion);
        return ConversionCreateResponse.builder()
                .id(conversion.getId().toString())
                .sourceCurrency(conversion.getSourceCurrency())
                .sourceAmount(conversion.getSourceAmount())
                .targetCurrency(conversion.getTargetCurrency())
                .targetAmount(conversion.getTargetAmount())
                .build();
    }
}
