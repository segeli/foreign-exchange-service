package com.foreignexchange.service;

import com.foreignexchange.entity.Conversion;
import com.foreignexchange.model.ConversionCreateRequest;
import com.foreignexchange.model.ConversionCreateResponse;
import com.foreignexchange.repository.ConversionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ConversionCreateServiceTest {

    @InjectMocks
    private ConversionCreateService conversionCreateService;

    @Mock
    private ConversionRepository conversionRepository;

    @Test
    void shouldCreate() {
        //given
        ConversionCreateRequest conversionCreateRequest = ConversionCreateRequest.builder()
                .sourceCurrency("USD")
                .sourceAmount(BigDecimal.valueOf(200d))
                .targetCurrency("TRY")
                .targetAmount(BigDecimal.valueOf(4507.28d))
                .build();

        Conversion mockConversion = mock(Conversion.class);
        UUID uuid = UUID.randomUUID();

        when(mockConversion.getId()).thenReturn(uuid);
        when(mockConversion.getSourceCurrency()).thenReturn("USD");
        when(mockConversion.getSourceAmount()).thenReturn(BigDecimal.valueOf(200d));
        when(mockConversion.getTargetCurrency()).thenReturn("TRY");
        when(mockConversion.getTargetAmount()).thenReturn(BigDecimal.valueOf(4507.28d));
        when(conversionRepository.save(any(Conversion.class))).thenReturn(mockConversion);

        //when
        ConversionCreateResponse actual = conversionCreateService.create(conversionCreateRequest);

        //then
        assertThat(actual)
                .returns(uuid.toString(), ConversionCreateResponse::getId)
                .returns(conversionCreateRequest.getSourceCurrency(), ConversionCreateResponse::getSourceCurrency)
                .returns(conversionCreateRequest.getSourceAmount(), ConversionCreateResponse::getSourceAmount)
                .returns(conversionCreateRequest.getTargetCurrency(), ConversionCreateResponse::getTargetCurrency)
                .returns(conversionCreateRequest.getTargetAmount(), ConversionCreateResponse::getTargetAmount);
    }
}